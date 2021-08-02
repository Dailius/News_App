package com.dailiusprograming.newsapp.main.news.sources

import android.os.Bundle
import android.view.View
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentSourcesBinding
import com.dailiusprograming.newsapp.main.news.NewsPagerContainer
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : BaseFragment(R.layout.fragment_sources) {
    private val binding by viewBinding(FragmentSourcesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickTextView()
    }

    private fun onClickTextView() {
        val args: String = resources.getString(R.string.temporary_sources)
        binding.textView.setOnClickListener {
            (parentFragment as NewsPagerContainer).openArticlesFragment(args)
        }
    }

    companion object {
        fun newInstance() = SourcesFragment()
    }
}
