package com.dailiusprograming.newsapp.utils.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dailiusprograming.newsapp.main.favorites.data.local.FavoritesDao
import com.dailiusprograming.newsapp.main.news.articles.data.local.ArticleDao
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleEntity
import com.dailiusprograming.newsapp.main.news.details.data.local.DetailsDao
import com.dailiusprograming.newsapp.main.news.sources.data.local.SourceDao
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceEntity
import com.dailiusprograming.newsapp.utils.storage.MainDatabase.Companion.DB_VERSION

@Database(
    entities = [
        SourceEntity::class,
        ArticleEntity::class
    ],
    version = DB_VERSION
)
@TypeConverters(TimeConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getSourceDao(): SourceDao
    abstract fun getArticleDao(): ArticleDao
    abstract fun getDetailsDao(): DetailsDao
    abstract fun getFavoritesDao(): FavoritesDao

    companion object {
        const val DB_VERSION = 1
    }
}
