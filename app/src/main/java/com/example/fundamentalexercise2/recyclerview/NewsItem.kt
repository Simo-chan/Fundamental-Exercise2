package com.example.fundamentalexercise2.recyclerview

import java.util.Date

data class NewsItem(
    val id: Int,
    val title: String,
    val imageURL: String?,
    val category: String,
    val publishDate: Date,
    val previewText: String,
    val fullText: String
) : java.io.Serializable


