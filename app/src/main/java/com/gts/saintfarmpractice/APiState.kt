package com.gts.saintfarmpractice

/**
 * Created by Shashank Khochikar on 8/24/2021
 */

sealed class APiState<T> {

    class Loading<T> : APiState<T>()
    data class Success<T>(val data: T) : APiState<T>()
    data class Error<T>(val message: String) : APiState<T>()

    companion object {

        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> error(message: String) = Error<T>(message)
    }
}