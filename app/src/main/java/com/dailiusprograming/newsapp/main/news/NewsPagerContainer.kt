package com.dailiusprograming.newsapp.main.news

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain

interface NewsPagerContainer {
    fun openArticlesFragment(sourceDomain: SourceDomain)
    fun openDetailsFragment(articleDomain: ArticleDomain)
}
