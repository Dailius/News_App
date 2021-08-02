package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.main.news.details.DetailsFragment
import com.dailiusprograming.newsapp.main.pager.MainPagerAdapter
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.fragment.openFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragmentContainer : BaseFragment(R.layout.fragment_favorites_container),
    FavoritesPagerContainer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openFavoritesFragment()
        }
    }

    private fun openFavoritesFragment() {
        openFragment(
            FavoritesFragment.newInstance(),
            addToBackStack = false,
            pageConstFromMainPagerAdapter = MainPagerAdapter.FAVORITES_PAGE
        )
    }

    override fun openDetailsFragment(args: String) {
        openFragment(
            DetailsFragment.newInstance(args),
            addToBackStack = true,
            pageConstFromMainPagerAdapter = MainPagerAdapter.FAVORITES_PAGE,
        )
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = FavoritesFragmentContainer()
    }
}
