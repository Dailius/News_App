package com.dailiusprograming.newsapp.utils.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dailiusprograming.newsapp.utils.storage.MainDatabase.Companion.DB_VERSION

@Database(
    entities = [],
    version = DB_VERSION
)
@TypeConverters(TimeConverter::class)
abstract class MainDatabase : RoomDatabase() {
    companion object{
        const val DB_VERSION = 1
    }
}