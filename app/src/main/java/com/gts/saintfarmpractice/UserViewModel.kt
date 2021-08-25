package com.gts.saintfarmpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    val allUsers:LiveData<List<User>>

    init {
        val dao= UserDatabase.getUserDatabase(application).getUserDao()
        repository= UserRepository(dao)
        allUsers = repository.allUsers
    }

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.registerUser(user)
    }

    fun checkEmail(email: String) = viewModelScope.launch  (Dispatchers.IO) {
        repository.checkEmail(email)
    }

}