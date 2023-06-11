package com.example.fundamentalexercise2

import java.util.Date

data class NewsItem(
    val id: Int,
    val title: String,
    val imageURL: String,
    val category: Category,
    val publishDate: Date,
    val previewText: String,
    val fullText: String
):java.io.Serializable

data class Category(
    val id: Int,
    val name: String
):java.io.Serializable
