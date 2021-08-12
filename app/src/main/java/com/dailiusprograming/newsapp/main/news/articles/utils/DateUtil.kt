package com.dailiusprograming.newsapp.main.news.articles.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getFormattedDate(date: Date = Date()): String =
        SimpleDateFormat("yyyy-MM-dd").format(date)

    fun getDateOfToday(): Date = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
    }.time
}
