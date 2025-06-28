package com.moataz.madarsoftdatabase.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun insertUser(user: User) {
        userDao.insertAll(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }
}