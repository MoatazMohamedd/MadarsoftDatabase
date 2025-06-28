package com.moataz.madarsoftdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "age") val age: String?,
    @ColumnInfo(name = "job_title") val jobTitle: String?,
    @ColumnInfo(name = "gender") val gender: String?
)