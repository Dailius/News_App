package com.dailiusprograming.newsapp.utils.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageGeneral @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPref =
        context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
    var isTutorialShown: Boolean
        get() = sharedPref.getBoolean(STORE_KEY_TUTORIAL_SCREEN, false)
        set(value) = sharedPref.edit().putBoolean(STORE_KEY_TUTORIAL_SCREEN, value).apply()

    companion object {
        const val STORE_KEY_TUTORIAL_SCREEN = "tutorial_screen"
        const val SHARED_PREF_FILE_NAME = "news_shared_pref"
    }
}
