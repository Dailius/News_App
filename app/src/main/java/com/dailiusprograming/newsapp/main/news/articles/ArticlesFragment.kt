package com.dailiusprograming.newsapp.main.news.articles

import android.os.Bundle
import android.view.View
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentArticlesBinding
import com.dailiusprograming.newsapp.main.news.NewsPagerContainer
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : BaseFragment(R.layout.fragment_articles) {
    private val binding by viewBinding(FragmentArticlesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener { openDetailsFragment() }
    }

    private fun openDetailsFragment() {
        (parentFragment as NewsPagerContainer).openDetailsFragment()
    }

    companion object {
        fun newInstance() = ArticlesFragment()
    }
}
