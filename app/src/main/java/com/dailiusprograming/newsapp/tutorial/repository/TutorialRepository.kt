package com.dailiusprograming.newsapp.tutorial.repository

import android.content.Context
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.tutorial.model.TutorialPage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TutorialRepository @Inject constructor(
    @ApplicationContext appContext: Context
) {
    private val context = appContext

    fun providePageList() = listOf(
        TutorialPage(
            image = R.drawable.news_sources,
            description = context.getString(R.string.tutorial_description_variety_sources)
        ),
        TutorialPage(
            image = R.drawable.latest_news,
            description = context.getString(R.string.tutorial_description_breaking_news)
        ),
        TutorialPage(
            image = R.drawable.save_favorites,
            description = context.getString(R.string.tutorial_description_save_favorites)
        )
    )
}
