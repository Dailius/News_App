package com.dailiusprograming.newsapp.main.news

import android.os.Bundle
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.main.news.articles.ArticlesFragment
import com.dailiusprograming.newsapp.main.news.details.DetailsFragment
import com.dailiusprograming.newsapp.main.news.sources.SourcesFragment
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.fragment.openFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragmentContainer : BaseFragment(R.layout.fragment_news_container), NewsPagerContainer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openSourcesFragment()
        }
    }

    private fun openSourcesFragment() {
        openFragment(SourcesFragment.newInstance(), addToBackStack = false)
    }

    override fun openArticlesFragment(args: String) {
        openFragment(ArticlesFragment.newInstance(args), addToBackStack = true)
    }

    override fun openDetailsFragment() {
        openFragment(DetailsFragment.newInstance(), addToBackStack = true)
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = NewsFragmentContainer()
    }
}
