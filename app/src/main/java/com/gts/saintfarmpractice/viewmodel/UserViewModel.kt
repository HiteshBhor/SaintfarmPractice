package com.gts.saintfarmpractice.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gts.saintfarmpractice.database.UserDatabase
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

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

    fun loginUser(email: String, password: String): LiveData<User>{
        return repository.loginUser(email, password).asLiveData()
    }

    fun checkEmail(email: String): LiveData<User> {
        return  repository.checkEmail(email).asLiveData()
    }

}