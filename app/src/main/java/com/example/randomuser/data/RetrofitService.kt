package com.example.randomuser.data

import com.example.randomuser.data.model.RandomUserResult
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitService {
    @GET("api/")
    suspend fun getRandomUser(): RandomUserResult
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}