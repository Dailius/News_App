package com.dailiusprograming.newsapp.main.news.articles.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.*

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles WHERE sourceId = :sourceId AND publishedAt >= :date ORDER BY publishedAt DESC")
    fun updateTodayNews(sourceId: String, date: Date): Observable<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE sourceId = :sourceId ORDER BY publishedAt DESC")
    fun updateNewestNews(sourceId: String): Observable<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE sourceId = :sourceId ORDER BY publishedAt DESC")
    fun updateAllTimeNews(sourceId: String): Observable<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(list: List<ArticleEntity>): Completable
}