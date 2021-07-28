package com.dailiusprograming.newsapp.main.favorites

import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_favorites

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
