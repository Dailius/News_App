package com.dailiusprograming.newsapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.activity.HandleBack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment is HandleBack && fragment.onBackPressed()){
            return
        }
        super.onBackPressed()
    }
}
