package com.dailiusprograming.newsapp.main.news.articles.data.model

import java.util.*

data class ArticleDomain(
    val url: String,
    val sourceId: String,
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
    var favorites: Boolean
) {
    fun toEntity() = ArticleEntity(
        url = url,
        sourceId = sourceId,
        sourceName = sourceName,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        favorites = favorites
    )
}
