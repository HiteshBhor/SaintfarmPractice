package com.gts.saintfarmpractice.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gts.saintfarmpractice.database.UserDatabase
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.models.WebUser
import com.gts.saintfarmpractice.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    val allUsers:LiveData<List<User>>
    val allWebUsers:LiveData<List<WebUser>>

    init {
        val dao= UserDatabase.getUserDatabase(application).getUserDao()
        repository= UserRepository(dao)
        allUsers = repository.allUsers
        allWebUsers = repository.allWebUsers
    }

    fun insertWebUser(webUser: WebUser)= viewModelScope.launch(Dispatchers.IO) {
        repository.insertWebUser(webUser)
    }

    fun deleteAllWebUsers() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllWebUsers()
    }

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.registerUser(user)
    }

    fun updateUserProfile(user: User): Int{
        return repository.updateUserProfile(user)
    }

    /*fun updateUserProfile(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUserProfile(user)
    }*/

    fun loginUser(email: String, password: String): LiveData<User>{
        return repository.loginUser(email, password).asLiveData()
    }

    fun checkEmail(email: String): LiveData<User> {
        return  repository.checkEmail(email).asLiveData()
    }

}