package com.dailiusprograming.newsapp.main.news.details.data.local

import androidx.room.Dao
import androidx.room.Query
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DetailsDao {
    @Query("SELECT * FROM articles WHERE url = :url")
    fun getArticle(url: String): Single<ArticleEntity>

    @Query("UPDATE articles SET favorites = :favorites WHERE url = :url")
    fun updateFavoritesItem(url: String, favorites: Boolean): Completable
}
