package com.dailiusprograming.newsapp.main.news

import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain

interface NewsPagerContainer {
    fun openArticlesFragment(sourceDomain: SourceDomain)
    fun openDetailsFragment(args: String)
}
