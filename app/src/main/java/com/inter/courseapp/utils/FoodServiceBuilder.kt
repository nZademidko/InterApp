package com.inter.courseapp.utils

import com.inter.courseapp.BASE_API_URL
import com.inter.courseapp.source.api.FoodService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FoodServiceBuilder {

    private fun getHttpClient() =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    private fun getConverterFactory() = GsonConverterFactory.create()

    fun build(): FoodService =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(getHttpClient())
            .addConverterFactory(getConverterFactory())
            .build()
            .create(FoodService::class.java)
}