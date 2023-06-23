package com.example.aulanova.repository

import com.example.aulanova.dao.AppDatabaseConnection
import com.example.aulanova.dao.ViagemDao
import com.example.aulanova.entity.Viagem
import android.app.Application

class ViagemRepository (app: Application) {

    private val viagemDao: ViagemDao

    init {
        viagemDao = AppDatabaseConnection
            .getDB(app).viagemDao()
    }

    suspend fun save(viagem: Viagem) {
        if (viagem.id == 0) {
            viagemDao.insert(viagem)
        }
        else {
            viagemDao.update(viagem)
        }
    }

    suspend fun findAll(): List<Viagem> = viagemDao.findAll()

    suspend fun findById(id: Int) = viagemDao.findById(id)

    suspend fun delete(viagem: Viagem) = viagemDao.delete(viagem)

}