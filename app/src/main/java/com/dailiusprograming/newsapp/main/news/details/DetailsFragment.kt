package com.dailiusprograming.newsapp.main.news.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import coil.load
import coil.size.ViewSizeResolver
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentDetailsBinding
import com.dailiusprograming.newsapp.main.MainActivity
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private var articleDetails: ArticleDomain? = null
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParcelableArgs()
        setUpViewModelObserver()
    }

    private fun getParcelableArgs(){
        val args: ArticleDomain? = arguments?.getParcelable(ARTICLE_KEY)
        viewModel.onArticleDetailsLoaded(args)
    }

    private fun setUpViewModelObserver() {
        viewModel.articleDetails.observe(viewLifecycleOwner, ::setUpWidgets)
    }

    private fun setUpWidgets(articleDomain: ArticleDomain) {
        articleDetails = articleDomain
        setUpAllToolbars()
        setUpTextView()
        setUpFavoritesCheckBox()
        setUpImageView()
    }

    private fun setUpAllToolbars() {
        setUpToolbar()
        setUpAppBarLayoutTransition()
        setUpCollapsingToolbar()
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                backToPreviousScreen()
            }
        }
    }

    private fun setUpAppBarLayoutTransition() {
        ViewCompat.setTransitionName(binding.appBarLayout, getString(R.string.details_transition))
    }

    private fun setUpCollapsingToolbar() {
        binding.collapsingToolbar.apply {
            title = articleDetails?.title
            setExpandedTitleColor(context.getColor(android.R.color.white))
            setCollapsedTitleTextColor(context.getColor(R.color.white))
            setContentScrimColor(context.getColor(R.color.primaryColor))
        }
    }

    private fun setUpTextView() {
        binding.apply {
            AuthorAndDateTextView.text =
                context?.getString(
                    R.string.details_author_and_dates,
                    articleDetails?.author,
                    articleDetails?.publishedAt
                )
            titleTextView.text = articleDetails?.title
            descriptionTextView.text = articleDetails?.description
            linkTextView.text = articleDetails?.url
        }
    }

    private fun setUpFavoritesCheckBox(){
        binding.favoriteCheckBox.isChecked = articleDetails?.favorites ?: false
    }

    private fun setUpImageView() {
        binding.apply {
            detailsImageView.load(articleDetails?.urlToImage) {
                crossfade(enable = true)
                crossfade(durationMillis = 500)
                size(ViewSizeResolver(detailsImageView))
            }
        }
    }

    private fun backToPreviousScreen() {
        activity?.onBackPressed()
    }

    companion object {
        private const val ARTICLE_KEY = "article_key"

        fun newInstance(articleDomain: ArticleDomain): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = Bundle().apply { putParcelable(ARTICLE_KEY, articleDomain) }
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
