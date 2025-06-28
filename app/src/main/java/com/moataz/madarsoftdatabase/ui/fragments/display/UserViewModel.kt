package com.moataz.madarsoftdatabase.ui.fragments.display

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.moataz.madarsoftdatabase.data.AppDatabase
import com.moataz.madarsoftdatabase.data.User
import com.moataz.madarsoftdatabase.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }
    fun deleteUser(user: User) = viewModelScope.launch { repository.deleteUser(user) }
}