package com.dailiusprograming.newsapp.main.news.articles.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity.Companion.DB_TABLE_ARTICLES
import java.util.*

@Entity(tableName = DB_TABLE_ARTICLES)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val sourceId: String,
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
    val favorites: Boolean
) {
    fun toDomain() = ArticleDomain(
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

    companion object {
        const val DB_TABLE_ARTICLES = "articles"
    }
}
