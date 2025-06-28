package com.moataz.madarsoftdatabase.ui.fragments.input

import com.moataz.madarsoftdatabase.data.AppDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.madarsoftdatabase.data.User
import com.moataz.madarsoftdatabase.data.UserRepository
import kotlinx.coroutines.launch

class InputViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun saveUser(user: User) = viewModelScope.launch { repository.insertUser(user) }
}