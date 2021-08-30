package com.gts.saintfarmpractice.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gts.saintfarmpractice.models.User
import com.gts.saintfarmpractice.models.WebUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert//(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWebUser(webUser: WebUser)

    @Query("SELECT * FROM WebUsers ORDER BY id ASC")
    fun getAllWebUsers(): LiveData<List<WebUser>>

    @Insert//(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(user: User)

    //@Update
    @Query("UPDATE Users SET firstName =:firstName,lastName =:lastName,password =:password,address =:address WHERE email =:email ")
     fun updateUserProfile(firstName: String?,
                                  lastName: String?,
                                  email: String?,
                                  password: String?,
                                  address: String?): Int

    @Query("SELECT * FROM Users WHERE email LIKE :email")
    fun checkEmail(email: String): Flow<User>

    @Query("SELECT * FROM Users WHERE email LIKE :email AND password LIKE :password ")
    fun loginUser(email: String, password: String): Flow<User>

    @Query("SELECT * FROM Users ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

}