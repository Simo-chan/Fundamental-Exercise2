package com.example.fundamentalexercise2.networking

import com.example.fundamentalexercise2.networking.dtos.MultimediaDTO
import com.example.fundamentalexercise2.networking.dtos.NewsDTO
import com.example.fundamentalexercise2.recyclerview.NewsItem
import java.util.Collections

object DTOtoNewsItemMapper {
    private const val MULTIMEDIA_TYPE = "image"
    private var currentId = 0

    fun map(dtos: List<NewsDTO>): List<NewsItem> {
        val items = ArrayList<NewsItem>()
        for (dto in dtos) {
            val newsItem = mapDTOtoNewsItem(dto)
            items.add(newsItem)
        }
        return Collections.unmodifiableList(items)
    }

    private fun mapDTOtoNewsItem(dto: NewsDTO): NewsItem {
        return NewsItem(
            title = dto.title,
            category = dto.subsection,
            publishDate = dto.publishedDate,
            previewText = dto.abstract,
            fullText = dto.url,
            id = generateUniqueId(),
            imageURL = multimediaDTOtoNewsItem(dto.multimedia)
        )

    }

    private fun multimediaDTOtoNewsItem(multimedias: List<MultimediaDTO>?): String? {
        if (multimedias == null || multimedias.isEmpty()) {
            return null
        }
        val imageIndex = multimedias.size - 1
        val multimedia = multimedias[imageIndex]

        if (multimedia.type != MULTIMEDIA_TYPE) {
            return null
        }
        return multimedia.url
    }

    private fun generateUniqueId(): Int {
        return currentId++
    }
}