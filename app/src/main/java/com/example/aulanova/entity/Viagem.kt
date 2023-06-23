package com.example.aulanova.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(foreignKeys = [
    ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE)
])
data class Viagem(
    val destino: String,
    val tipo: TipoViagem,
    val data_inicio: String,
    val data_final: String,
    val userId: Int,
    val orcamento: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}

enum class TipoViagem {
    LAZER,
    NEGOCIO
}