package com.dailiusprograming.newsapp.utils.fragment

import androidx.fragment.app.Fragment
import com.dailiusprograming.newsapp.utils.activity.HandleBack

abstract class BaseFragment(layoutRes: Int) : Fragment(layoutRes), HandleBack {
    override fun onBackPressed() = false
}
