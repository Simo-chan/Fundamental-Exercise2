package com.example.fundamentalexercise2.networking.dtos

import com.google.gson.annotations.SerializedName

data class TopStoriesResponse(
    @SerializedName("results")
    val results: List<NewsDTO>
)
