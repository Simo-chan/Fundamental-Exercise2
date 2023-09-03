package com.example.fundamentalexercise2.networking

import com.example.fundamentalexercise2.BuildConfig
import com.example.fundamentalexercise2.networking.endpoints.NewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val API_KEY = BuildConfig.API_KEY
    private const val BASE_URL = "https://api.nytimes.com/svc/"

    private val apiKeyInterceptor = ApiKeyInterceptor(API_KEY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}