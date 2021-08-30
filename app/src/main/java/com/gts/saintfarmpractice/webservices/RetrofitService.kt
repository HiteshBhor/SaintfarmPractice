package com.gts.saintfarmpractice.webservices

import com.gts.saintfarmpractice.models.ListUsersModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://reqres.in/api/"

interface  RetrofitService {


    @GET("users?page=2")
    suspend fun getAllUsers(): Response<ListUsersModel>

    companion object{
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return  retrofitService!!
        }

        private fun getOkHttpClient(): OkHttpClient {
            val cacheSize = (10 * 1024 * 1024).toLong()
            //val myCache = Cache(context.cacheDir, cacheSize)
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            //interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            return OkHttpClient.Builder()
                //.cache(myCache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()
        }

    }
}