package com.example.fundamentalexercise2.networking.dtos

import com.google.gson.annotations.SerializedName

data class MultimediaDTO(
    @SerializedName("url")
    val url: String,

    @SerializedName("type")
    val type: String
)
