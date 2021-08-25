package com.gts.saintfarmpractice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM Users WHERE email LIKE :email")
    fun checkEmail(email: String): LiveData<User>

    @Query("SELECT * FROM Users ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

}