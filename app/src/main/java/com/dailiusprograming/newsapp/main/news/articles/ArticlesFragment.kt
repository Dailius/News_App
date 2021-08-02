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
        setUpToolBar()
        setUpTextView()
    }

    private fun setUpTextView(){
        binding.textView.setOnClickListener { openDetailsFragment() }
        binding.textView.text =
            resources.getString(
                R.string.temporary_transfer_args,
                arguments?.getString(SOURCE_KEY),
                getString(R.string.temporary_articles)
            )
    }

    private fun openDetailsFragment() {
        (parentFragment as NewsPagerContainer).openDetailsFragment()
    }

    private fun setUpToolBar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                backToPreviousScreen()
            }
        }
    }

    private fun backToPreviousScreen() {
        activity?.onBackPressed()
    }

    companion object {
        private const val SOURCE_KEY = "source_key"

        fun newInstance(args: String): ArticlesFragment {
            val fragment = ArticlesFragment()
            fragment.arguments = Bundle().apply { putString(SOURCE_KEY, args) }
            return fragment
        }
    }
}
