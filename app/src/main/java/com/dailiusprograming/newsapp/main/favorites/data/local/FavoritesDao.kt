package com.dailiusprograming.newsapp.main.favorites.data.local

import androidx.room.Dao
import androidx.room.Query
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM articles WHERE favorites = :favorites ORDER BY publishedAt DESC")
    fun getFavorites(favorites: Boolean = true): Observable<List<ArticleEntity>>
}
