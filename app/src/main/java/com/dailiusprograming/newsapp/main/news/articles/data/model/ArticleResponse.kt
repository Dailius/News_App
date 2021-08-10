package com.dailiusprograming.newsapp.main.news.articles.data.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    val status: String,
    val totalResults: Long,
    @SerializedName("ArticleResponse")
    val articles: List<ArticleItemResponse>
)
