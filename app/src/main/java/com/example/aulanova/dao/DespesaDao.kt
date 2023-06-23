package com.example.aulanova.dao

import androidx.room.*
import com.example.aulanova.entity.Despesa

@Dao
interface DespesaDao {

    @Insert()
    suspend fun insert(despesa: Despesa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(despesa: Despesa)

    @Delete
    suspend fun delete(despesa: Despesa)

    @Query("select * from Despesa s where s.viagemId = :viagemId  order by descricao")
    suspend fun findAllByViagem(viagemId: Int): List<Despesa>

    @Query("select * from Despesa s where s.id = :id")
    suspend fun findById(id: Int): Despesa?

    @Query("SELECT * FROM Despesa WHERE viagemId = :viagemId")
    suspend fun findAllByViagemId(viagemId: Int): List<Despesa>

    @Query("SELECT * FROM despesa WHERE viagemId = :viagemId")
    suspend fun getDespesasPorViagemId(viagemId: Int): List<Despesa>


}