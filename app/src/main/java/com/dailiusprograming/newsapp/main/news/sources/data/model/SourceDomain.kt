package com.dailiusprograming.newsapp.main.news.sources.data.model

data class SourceDomain(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) {
    fun toEntity() = SourceEntity(
        id = id,
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country
    )
}
