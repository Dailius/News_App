package com.dailiusprograming.newsapp.main.news.details.repository

import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.details.data.local.DetailsDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailsLocalRepository @Inject constructor(
    private val detailsDao: DetailsDao
) {
    fun getArticleDetails(articleId: String): Single<ArticleDomain> =
        detailsDao.getArticle(url = articleId)
            .map { articleEntity -> articleEntity.toDomain() }

    fun updateFavoritesItem(url: String, favorites: Boolean) =
        detailsDao.updateFavoritesItem(url = url, favorites = favorites)
}
