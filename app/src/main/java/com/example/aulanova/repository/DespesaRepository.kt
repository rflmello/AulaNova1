package com.example.aulanova.repository

import android.app.Application
import com.example.aulanova.dao.AppDatabaseConnection
import com.example.aulanova.dao.DespesaDao
import com.example.aulanova.entity.Despesa

class DespesaRepository (app: Application) {

    private val despesaDao: DespesaDao

    init {
        despesaDao = AppDatabaseConnection
            .getDB(app).despesaDao()
    }

    suspend fun save(despesa: Despesa) {
        if (despesa.id == 0) {
            despesaDao.insert(despesa)
        }
        else {
            despesaDao.update(despesa)
        }
    }

    suspend fun findAllByViagem(viagemId: Int): List<Despesa> = despesaDao.findAllByViagem(viagemId)

    suspend fun findById(id: Int) = despesaDao.findById(id)

    suspend fun delete(despesa: Despesa) = despesaDao.delete(despesa)

}