package com.dailiusprograming.newsapp.main.news.articles

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentArticlesBinding
import com.dailiusprograming.newsapp.main.MainActivity
import com.dailiusprograming.newsapp.main.news.NewsPagerContainer
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleError
import com.dailiusprograming.newsapp.main.news.articles.utils.UiFilter
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import com.dailiusprograming.newsapp.utils.activity.displayMessageWithRefreshBtn
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.fragment.displayFeatureScreen
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : BaseFragment(R.layout.fragment_articles) {
    private val binding by viewBinding(FragmentArticlesBinding::bind)
    private var sourceDomain: SourceDomain? = null
    private var recyclerAdapter: ArticlesAdapter? = null
    private val viewModel: ArticlesViewModel by viewModels()

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
        setUpRecyclerView()
        setUpToolBar()
        onChipButtonsClickListener()
        onArticlesRefreshListener()
        viewModel.onRefreshSelected()
    }

    private fun getParcelableArgs() {
        sourceDomain = arguments?.getParcelable(SOURCE_KEY)
        viewModel.onSourceIdLoaded(sourceDomain?.id)
    }

    private fun setUpViewModelObserver() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, ::isSwipeRefreshing)
        viewModel.articleList.observe(viewLifecycleOwner, ::submitArticleList)
        viewModel.errorMessage.observe(viewLifecycleOwner, ::handleErrorDisplay)
    }

    private fun onChipButtonsClickListener() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.popularTodayChip.id ->
                    viewModel.onFilterTypeChange(UiFilter.Type.POPULAR_TODAY)

                binding.popularAllTimeChip.id ->
                    viewModel.onFilterTypeChange(UiFilter.Type.POPULAR_ALL_TIME)

                binding.newestChip.id ->
                    viewModel.onFilterTypeChange(UiFilter.Type.NEWEST)

                else ->
                    viewModel.onFilterTypeChange(UiFilter.Type.DEFAULT)
            }
        }
    }

    private fun onArticlesRefreshListener() {
        binding.swipeArticleRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshSelected()
        }
    }

    private fun isSwipeRefreshing(isEnabled: Boolean) {
        binding.swipeArticleRefreshLayout.isRefreshing = isEnabled
    }

    private fun handleErrorDisplay(error: ArticleError) {
        val isAdapterListEmpty = recyclerAdapter?.currentList?.isEmpty() == true
        setDisplayFeatureScreen(isAdapterListEmpty)

        (activity as MainActivity).displayMessageWithRefreshBtn(
            error.message ?: getString(R.string.sources_unknown_error)
        ) { viewModel.onRefreshSelected() }
    }

    private fun setDisplayFeatureScreen(isEmptyList: Boolean) {
        displayFeatureScreen(
            isEmptyList,
            binding.articleRecyclerView,
            binding.articlesNotificationScreen
        )
    }

    private fun setUpToolBar() {
        binding.toolbar.apply {
            title = sourceDomain?.name
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                backToPreviousScreen()
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.articleRecyclerView.apply {
            recyclerAdapter = ArticlesAdapter { article -> onArticlesItemClick(article) }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun submitArticleList(list: List<ArticleDomain>) {
        recyclerAdapter?.submitList(list)
        binding.articleRecyclerView.adapter = recyclerAdapter
        setDisplayFeatureScreen(list.isEmpty())
    }

    private fun onArticlesItemClick(articleDomain: ArticleDomain) {
        openDetailsFragment(articleDomain)
    }

    private fun openDetailsFragment(articleDomain: ArticleDomain) {
        (parentFragment as NewsPagerContainer).openDetailsFragment(articleDomain)
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

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter = null
    }
}
