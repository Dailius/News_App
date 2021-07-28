package com.dailiusprograming.newsapp.main.news.sources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment

class SourcesFragment : BaseFragment() {
    override val layoutRes get() = R.layout.fragment_sources

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sources, container, false)
    }

    companion object {
    }
}
