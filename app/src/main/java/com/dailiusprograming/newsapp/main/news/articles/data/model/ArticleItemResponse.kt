package com.dailiusprograming.newsapp.main.news.articles.data.model

import java.util.*

data class ArticleItemResponse(
    val url: String,
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
    val favorites: Boolean = false
) {
    fun toDomain() = ArticleDomain(
        url = url,
        sourceId = source.id,
        sourceName = source.name,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        favorites = favorites
    )
}
