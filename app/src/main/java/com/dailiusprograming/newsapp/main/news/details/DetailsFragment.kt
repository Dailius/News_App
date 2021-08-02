package com.dailiusprograming.newsapp.main.news.details

import android.os.Bundle
import android.view.View
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentDetailsBinding
import com.dailiusprograming.newsapp.main.MainActivity
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)

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

    private fun setUpTextView() {
        binding.textView.text =
            resources.getString(
                R.string.temporary_transfer_args,
                arguments?.getString(ARTICLE_KEY),
                getString(R.string.temporary_details)
            )
    }

    private fun backToPreviousScreen() {
        activity?.onBackPressed()
    }

    companion object {
        private const val ARTICLE_KEY = "article_key"

        fun newInstance(args: String): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = Bundle().apply { putString(ARTICLE_KEY, args) }
            return fragment
        }
    }

    override fun onResume() {
        (activity as MainActivity).apply {
            window.statusBarColor = getColor(R.color.transparent)
        }
        super.onResume()
    }

    override fun onPause() {
        (activity as MainActivity).apply {
            window.statusBarColor = getColor(R.color.primaryDarkColor)
        }
        super.onPause()
    }
}
