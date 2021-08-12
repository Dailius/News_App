package com.dailiusprograming.newsapp.utils.storage

import android.content.Context
import androidx.room.Room
import com.dailiusprograming.newsapp.main.news.articles.data.local.ArticleDao
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

    private const val DB_NAME = "main.db"
}
