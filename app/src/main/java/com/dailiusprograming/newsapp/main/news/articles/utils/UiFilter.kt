package com.dailiusprograming.newsapp.main.news.articles.utils

data class UiFilter(val type: Type) {
    enum class Type {
        POPULAR_TODAY,
        POPULAR_ALL_TIME,
        NEWEST,
        DEFAULT
    }
}
