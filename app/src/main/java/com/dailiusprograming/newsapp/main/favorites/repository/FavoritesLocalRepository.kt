package com.dailiusprograming.newsapp.main.favorites.repository

import com.dailiusprograming.newsapp.main.favorites.data.local.FavoritesDao
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FavoritesLocalRepository @Inject constructor(
    private val favoritesDao: FavoritesDao
) {
    fun getFavorites(): Observable<List<ArticleDomain>> =
        favoritesDao.getFavorites()
            .map { articleEntity -> articleEntity.map(ArticleEntity::toDomain) }
}
