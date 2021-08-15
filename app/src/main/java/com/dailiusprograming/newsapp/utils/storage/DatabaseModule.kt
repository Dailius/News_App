package com.dailiusprograming.newsapp.utils.storage

import android.content.Context
import androidx.room.Room
import com.dailiusprograming.newsapp.main.favorites.data.local.FavoritesDao
import com.dailiusprograming.newsapp.main.news.articles.data.local.ArticleDao
import com.dailiusprograming.newsapp.main.news.details.data.local.DetailsDao
import com.dailiusprograming.newsapp.main.news.sources.data.local.SourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): MainDatabase =
        Room.databaseBuilder(appContext, MainDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSourceDao(mainDatabase: MainDatabase): SourceDao =
        mainDatabase.getSourceDao()

    @Provides
    fun provideArticleDao(mainDatabase: MainDatabase): ArticleDao =
        mainDatabase.getArticleDao()

    @Provides
    fun provideDetailsDao(mainDatabase: MainDatabase): DetailsDao =
        mainDatabase.getDetailsDao()

    @Provides
    fun provideFavoritesDao(mainDatabase: MainDatabase): FavoritesDao =
        mainDatabase.getFavoritesDao()

    private const val DB_NAME = "main.db"
}
