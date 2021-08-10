package com.dailiusprograming.newsapp.main.news.articles

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentArticlesBinding
import com.dailiusprograming.newsapp.main.news.NewsPagerContainer
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : BaseFragment(R.layout.fragment_articles) {
    private val binding by viewBinding(FragmentArticlesBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpTextView()
    }

    private fun setUpToolBar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                backToPreviousScreen()
            }
        }
    }

    private fun setUpTextView(){
        onClickTextView()
        val sourceDomain = arguments?.getParcelable<SourceDomain>(SOURCE_KEY)
        binding.textView.text =
            resources.getString(
                R.string.temporary_transfer_args,
                sourceDomain?.name,
                getString(R.string.temporary_articles)
            )
    }

    private fun onClickTextView() {
        val args: String = resources.getString(R.string.temporary_articles)
        binding.textView.setOnClickListener { openDetailsFragment(args)}
        }

    private fun openDetailsFragment(args: String) {
        (parentFragment as NewsPagerContainer).openDetailsFragment(args)
    }

    private fun backToPreviousScreen() {
        activity?.onBackPressed()
    }

    companion object {
        private const val SOURCE_KEY = "source_key"

        fun newInstance(sourceDomain: SourceDomain): ArticlesFragment {
            val fragment = ArticlesFragment()
            fragment.arguments = Bundle().apply { putParcelable(SOURCE_KEY, sourceDomain) }
            return fragment
        }
    }
}
