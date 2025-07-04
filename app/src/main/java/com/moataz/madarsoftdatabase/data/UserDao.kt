package com.moataz.madarsoftdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert
    suspend fun insertAll(user: User)

    @Delete
    suspend fun delete(user: User)
}