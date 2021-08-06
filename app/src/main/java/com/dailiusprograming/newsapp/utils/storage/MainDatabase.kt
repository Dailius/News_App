package com.dailiusprograming.newsapp.utils.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dailiusprograming.newsapp.main.news.sources.data.local.SourceDao
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceEntity
import com.dailiusprograming.newsapp.utils.storage.MainDatabase.Companion.DB_VERSION

@Database(
    entities = [SourceEntity::class],
    version = DB_VERSION
)
@TypeConverters(TimeConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getSourceDao(): SourceDao

    companion object {
        const val DB_VERSION = 1
    }
}
