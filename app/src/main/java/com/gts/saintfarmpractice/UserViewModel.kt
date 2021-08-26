package com.gts.saintfarmpractice

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    val allUsers:LiveData<List<User>>
    var checkEmail: LiveData<User>? = null

    init {
        val dao= UserDatabase.getUserDatabase(application).getUserDao()
        repository= UserRepository(dao)
        allUsers = repository.allUsers
    }

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.registerUser(user)
    }

    fun checkEmail(email: String): LiveData<User> {
        return  repository.checkEmail(email).asLiveData()
    }

}