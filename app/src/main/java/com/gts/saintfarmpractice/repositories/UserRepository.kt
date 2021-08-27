package com.gts.saintfarmpractice.repositories

import androidx.lifecycle.LiveData
import com.gts.saintfarmpractice.dao.UserDao
import com.gts.saintfarmpractice.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allUsers:LiveData<List<User>> = userDao.getAllUsers()
    var checkEmail: LiveData<User>? = null

    suspend fun registerUser(user: User){
        userDao.registerUser(user)
    }

    fun loginUser(email: String, password: String): Flow<User>{
        return userDao.loginUser(email, password)
    }

    fun checkEmail(email: String): Flow<User> {
        return  userDao.checkEmail(email)
    }
}