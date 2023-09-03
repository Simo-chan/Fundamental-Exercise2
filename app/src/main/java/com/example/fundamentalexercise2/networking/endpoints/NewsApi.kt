package com.example.fundamentalexercise2.networking.endpoints

import com.example.fundamentalexercise2.networking.dtos.TopStoriesResponse
import retrofit2.Response
import retrofit2.http.GET

const val END_POINT = "topstories/v2/arts.json"

interface NewsApi {
    @GET(END_POINT)
    suspend fun getNews(): Response<TopStoriesResponse>
}