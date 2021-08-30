package com.gts.saintfarmpractice.repositories

import androidx.lifecycle.LiveData
import com.gts.saintfarmpractice.dao.UserDao
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.models.WebUser
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allUsers:LiveData<List<User>> = userDao.getAllUsers()
    val allWebUsers:LiveData<List<WebUser>> = userDao.getAllWebUsers()

    suspend fun insertWebUser(webUser: WebUser){
        userDao.insertWebUser(webUser)
    }

    suspend fun deleteAllWebUsers(){
        userDao.deleteAllWebUsers()
    }

    suspend fun registerUser(user: User){
        userDao.registerUser(user)
    }

    fun updateUserProfile(user: User): Int {
        return userDao.updateUserProfile(user.firstName,user.lastName,user.email,user.password,user.address)
    }

    fun loginUser(email: String, password: String): Flow<User>{
        return userDao.loginUser(email, password)
    }

    fun checkEmail(email: String): Flow<User> {
        return  userDao.checkEmail(email)
    }

}