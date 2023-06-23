package com.example.aulanova.dao

import androidx.room.*
import com.example.aulanova.entity.User

@Dao
interface UserDao {

    @Insert()
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("select * from User order by name")
    suspend fun findAll(): List<User>

    @Query("select * from User c where c.id = :id")
    suspend fun findById(id: Int): User?

    @Query("select * from User u where u.name = :name and u.password = :password")
    suspend fun findUserByUsernameAndPassword(name: String, password: String): User?;
}