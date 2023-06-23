package com.example.aulanova.repository

import android.app.Application
import com.example.aulanova.dao.AppDatabaseConnection
import com.example.aulanova.dao.UserDao
import com.example.aulanova.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository (app: Application) {

    private val userDao: UserDao
    private val coroutine =  CoroutineScope(Dispatchers.Main)
    init {
        userDao = AppDatabaseConnection
            .getDB(app).userDao()
    }

    suspend fun save(user: User) {
        if (user.id == 0) {
            userDao.insert(user)
        }
        else {
            userDao.update(user)
        }
    }
    fun addUser(user: User) {
        coroutine.launch(Dispatchers.IO) {
            userDao.insert(user)
        }

    }

    suspend fun findAll(): List<User> = userDao.findAll()
    suspend fun findById(id: Int) = userDao.findById(id)
    suspend fun delete(user: User) = userDao.delete(user)
    suspend fun findUserByUsernameAndPassword(name: String, password: String) =
        userDao.findUserByUsernameAndPassword(name, password)

}