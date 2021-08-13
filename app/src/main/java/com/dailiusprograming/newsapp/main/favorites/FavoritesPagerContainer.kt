package com.dailiusprograming.newsapp.main.favorites

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain

interface FavoritesPagerContainer {
    fun openDetailsFragment(articleDomain: ArticleDomain)
}
