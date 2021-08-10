package com.dailiusprograming.newsapp.main.news.articles.data.model

data class ArticleError(
    val type: Type = Type.DEFAULT,
    val message: String? = null
) {
    enum class Type {
        DEFAULT,
        ACTION
    }
}
