package com.dailiusprograming.newsapp.main.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentNewsBinding
import com.dailiusprograming.newsapp.main.news.articles.ArticlesFragment
import com.dailiusprograming.newsapp.main.news.details.DetailsFragment
import com.dailiusprograming.newsapp.main.news.sources.SourcesFragment
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.fragment.openFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment(), NewsPagerContainer {
    override val layoutRes get() = R.layout.fragment_news
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openSourcesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun openSourcesFragment() {
        openFragment(SourcesFragment.newInstance(), addToBackStack = false)
    }

    override fun openArticlesFragment() {
        openFragment(ArticlesFragment.newInstance(), addToBackStack = true)
    }

    override fun openDetailsFragment() {
        openFragment(DetailsFragment.newInstance(), addToBackStack = true)
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
