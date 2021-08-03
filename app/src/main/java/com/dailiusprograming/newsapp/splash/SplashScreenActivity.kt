package com.dailiusprograming.newsapp.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dailiusprograming.newsapp.main.MainActivity
import com.dailiusprograming.newsapp.utils.storage.StorageGeneral
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    @Inject
    lateinit var storageGeneral: StorageGeneral

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isTutorialScreenDisplayed = storageGeneral.isTutorialShown

        if (isTutorialScreenDisplayed) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
//            startActivity(Intent(this, TutorialActivity::class.java))
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}
