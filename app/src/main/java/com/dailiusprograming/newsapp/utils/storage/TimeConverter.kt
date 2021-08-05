package com.dailiusprograming.newsapp.utils.storage

import androidx.room.TypeConverter
import java.util.*

class TimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let(::Date)

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time
}
