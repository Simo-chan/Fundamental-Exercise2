package com.example.fundamentalexercise2.networking

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_HEADER = "api_key"

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter(API_KEY_HEADER, apiKey)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}