package com.gts.saintfarmpractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gts.saintfarmpractice.APiState
import com.gts.saintfarmpractice.models.ListUsersModel
import com.gts.saintfarmpractice.repositories.UserListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Shashank Khochikar on 8/24/2021
 */
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class UserListViewModel (private val repository: UserListRepository) : ViewModel() {

    private val userListLiveData = MutableLiveData<APiState<ListUsersModel>>()
    val userListHoldLiveData: LiveData<APiState<ListUsersModel>> get() = userListLiveData

    fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers().collect {
                userListLiveData.value = it
            }
        }
    }
}