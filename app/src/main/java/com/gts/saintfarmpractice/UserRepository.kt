package com.gts.saintfarmpractice

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allUsers:LiveData<List<User>> = userDao.getAllUsers()
    var checkEmail: LiveData<User>? = null

    suspend fun registerUser(user: User){
        userDao.registerUser(user)
    }

    fun checkEmail(email: String): Flow<User> {
        return  userDao.checkEmail(email)
    }
}