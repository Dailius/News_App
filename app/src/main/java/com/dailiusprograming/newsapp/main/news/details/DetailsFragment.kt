package com.dailiusprograming.newsapp.main.news.details

import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    companion object {
        fun newInstance() = DetailsFragment()
    }
}
