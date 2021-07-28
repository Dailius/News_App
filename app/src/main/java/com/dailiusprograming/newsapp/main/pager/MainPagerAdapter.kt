package com.dailiusprograming.newsapp.main.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dailiusprograming.newsapp.main.news.NewsFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val mapOfFragments = mapOf<Int, Fragment>(
        NEWS_PAGE to NewsFragment.newInstance(),
        FAVORITE_PAGE to FavoriteFragment.newInstance(),
        ABOUT_PAGE to AboutFragment.newInstance()
    )

    override fun getItemCount() = NUMBER_OF_PAGES

    override fun createFragment(position: Int) = mapOfFragments[position]

    companion object {
        const val NEWS_PAGE = 0
        const val FAVORITE_PAGE = 1
        const val ABOUT_PAGE = 2

        const val NUMBER_OF_PAGES = 3
    }
}
