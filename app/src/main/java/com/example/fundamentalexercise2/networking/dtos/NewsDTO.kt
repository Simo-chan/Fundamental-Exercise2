package com.example.fundamentalexercise2.networking.dtos

import com.example.fundamentalexercise2.networking.dtos.MultimediaDTO
import com.google.gson.annotations.SerializedName
import java.util.Date

data class NewsDTO(
    @SerializedName("subsection")
    val subsection: String,

    @SerializedName("title")
    val title:String,

    @SerializedName("abstract")
    val abstract:String,

    @SerializedName("url")
    val url:String,

    @SerializedName("published_date")
    val publishedDate:Date,

    @SerializedName("multimedia")
    val multimedia:List<MultimediaDTO>
)
