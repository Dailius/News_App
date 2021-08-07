package com.dailiusprograming.newsapp.main.news.sources.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceEntity.Companion.DB_TABLE_SOURCE

@Entity(tableName = DB_TABLE_SOURCE)
data class SourceEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) {
    fun toDomain() = SourceDomain(
        id = id,
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country
    )

    companion object {
        const val DB_TABLE_SOURCE = "source"
    }
}
