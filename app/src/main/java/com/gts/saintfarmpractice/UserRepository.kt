package com.gts.saintfarmpractice

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val allUsers:LiveData<List<User>> = userDao.getAllUsers()

    suspend fun registerUser(user: User){
        userDao.registerUser(user)
    }

    suspend fun checkEmail(email: String){
        userDao.checkEmail(email)
    }
}