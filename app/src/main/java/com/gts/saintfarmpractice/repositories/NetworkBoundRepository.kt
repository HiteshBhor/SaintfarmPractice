package com.gts.saintfarmpractice.repositories


import android.util.Log
import androidx.annotation.MainThread
import com.gts.saintfarmpractice.APiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow
import retrofit2.Response

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<T> {

    fun asFlow() = flow<APiState<T>> {

        // Emit Loading State
        emit(APiState.loading())
        try {
            // Fetch latest data from remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val remotePosts = apiResponse.body()
            // Check for response validation
            if (apiResponse.isSuccessful && remotePosts != null) {
                /*val responseStr = Gson().toJson(remotePosts)
                var jsonObject = Gson().fromJson(responseStr,JsonObject::class.java)
                if (jsonObject.has("message") && jsonObject.get("message") != null
                    && (jsonObject.get("message").asString).equals("InvalidTOken", true)
                ) {
                   // logoutUser()

                } else {
                    emit(APiState.success(remotePosts))
                }*/
                emit(APiState.success(remotePosts))
            } else {
                // Something went wrong! Emit Error state.
                emit(APiState.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            Log.e("Network Exception ", "" + e.message)
            // Exception occurred! Emit error
            emit(APiState.error("Network error! Can't get latest data."))
            e.printStackTrace()
        }
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<T>
}