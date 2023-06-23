package com.example.aulanova.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = [
    ForeignKey(entity = Viagem::class,
        parentColumns = ["id"],
        childColumns = ["viagemId"],
        onDelete = ForeignKey.CASCADE)
])
data class Despesa(
    val data: String,
    val valor: Double,
    val descricao: String,
    val tipo: String,
    val viagemId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}