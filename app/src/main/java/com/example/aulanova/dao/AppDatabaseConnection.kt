package com.example.aulanova.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aulanova.entity.Despesa
import com.example.aulanova.entity.Viagem
import com.example.aulanova.entity.User

@Database(entities = arrayOf(User::class, Viagem::class, Despesa::class), version = 6 )
abstract class AppDatabaseConnection: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun despesaDao(): DespesaDao
    abstract fun viagemDao(): ViagemDao

    companion object {
        var connection: AppDatabaseConnection? = null

        fun getDB(context: Context): AppDatabaseConnection {
            val temp = connection
            if (temp != null) {
                return temp
            }
            else {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabaseConnection::class.java,
                    "meuex-30"
                ).fallbackToDestructiveMigration()
                .build()
                connection = instance
                return instance
            }
        }

    }
}