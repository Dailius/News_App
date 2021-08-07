package com.dailiusprograming.newsapp.main.news.sources.data.model

data class SourceItemResponse(
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
}
