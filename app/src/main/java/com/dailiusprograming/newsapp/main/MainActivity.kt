package com.dailiusprograming.newsapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.main.pager.MainPagerFragment
import com.dailiusprograming.newsapp.utils.activity.HandleBack
import com.dailiusprograming.newsapp.utils.activity.openFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            openFragment(
                MainPagerFragment.newInstance(),
                addToBackStack = false
            )
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainer)
        if (fragment is HandleBack && fragment.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }
}
