package com.example.aulanova.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name: String,
    val password: String,
    val email: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}