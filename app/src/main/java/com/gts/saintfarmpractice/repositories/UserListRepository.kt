package com.gts.saintfarmpractice.repositories

import com.gts.saintfarmpractice.APiState
import com.gts.saintfarmpractice.models.ListUsersModel
import com.gts.saintfarmpractice.webservices.RetrofitService
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class UserListRepository constructor(private val apiService: RetrofitService) {

    /*
   * notifyMeProduct
   * */
    fun getAllUsers(): Flow<APiState<ListUsersModel>> {
        return object : NetworkBoundRepository<ListUsersModel>() {
            override suspend fun fetchFromRemote(): Response<ListUsersModel> =
                apiService.getAllUsers()
        }.asFlow().flowOn(Dispatchers.IO)
    }
}