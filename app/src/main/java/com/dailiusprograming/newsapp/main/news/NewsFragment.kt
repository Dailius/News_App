package com.dailiusprograming.newsapp.main.news

import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_news

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = NewsFragment()
    }
}
