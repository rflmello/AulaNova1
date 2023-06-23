package com.example.aulanova.dao

import androidx.room.*
import com.example.aulanova.entity.Viagem

@Dao
interface ViagemDao {

    @Insert()
    suspend fun insert(viagem: Viagem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(viagem: Viagem)

    @Delete
    suspend fun delete(viagem: Viagem)

    @Query("select * from Viagem order by destino")
    suspend fun findAll(): List<Viagem>

    @Query("select * from Viagem c where c.id = :id")
    suspend fun findById(id: Int): Viagem?

    @Query("SELECT SUM(valor) FROM Despesa WHERE viagemId = :viagemId")
    fun getValorTotalDespesas(viagemId: Int): Double

}