package com.dailiusprograming.newsapp.main.about

import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_about

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = AboutFragment()
    }
}
