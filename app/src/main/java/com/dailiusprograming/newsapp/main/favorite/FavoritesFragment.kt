package com.dailiusprograming.newsapp.main.favorite

import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment

class FavoritesFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_favorites

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
