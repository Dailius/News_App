package com.dailiusprograming.newsapp.main.news.articles.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
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
) : Parcelable {
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
