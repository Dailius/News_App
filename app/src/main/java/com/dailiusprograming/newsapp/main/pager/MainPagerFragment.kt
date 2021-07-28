package com.dailiusprograming.newsapp.main.pager

import androidx.viewpager.widget.PagerAdapter
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment

class MainPagerFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_main_pager
    private lateinit var adapter: PagerAdapter
}
