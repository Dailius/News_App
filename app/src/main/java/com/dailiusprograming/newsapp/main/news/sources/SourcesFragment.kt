package com.dailiusprograming.newsapp.main.news.sources

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentSourcesBinding
import com.dailiusprograming.newsapp.main.MainActivity
import com.dailiusprograming.newsapp.main.news.NewsPagerContainer
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceDomain
import com.dailiusprograming.newsapp.main.news.sources.data.model.SourceError
import com.dailiusprograming.newsapp.utils.activity.displayMessageWithRefreshBtn
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : BaseFragment(R.layout.fragment_sources) {
    private val binding by viewBinding(FragmentSourcesBinding::bind)
    private val viewModel: SourcesViewModel by viewModels()
    private var recyclerAdapter: SourcesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModelObserver()
        setUpOnRefreshListener()
        viewModel.onRefreshSelected()
    }

    private fun setUpViewModelObserver() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, ::isSwipeRefreshing)
        viewModel.sourceList.observe(viewLifecycleOwner, ::submitSourceList)
        viewModel.errorMessage.observe(viewLifecycleOwner, ::handleErrorDisplay)
    }

    private fun setUpRecyclerView() {
        binding.sourcesRecyclerView.apply {
            recyclerAdapter = SourcesAdapter { source -> setUpOnItemClick(source) }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun submitSourceList(list: List<SourceDomain>) {
        recyclerAdapter?.submitList(list)
        binding.sourcesRecyclerView.adapter = recyclerAdapter
    }

    private fun handleErrorDisplay(error: SourceError) {
        val isAdapterListEmpty = recyclerAdapter?.currentList?.isEmpty() == true
        handleScreenDisplay(isAdapterListEmpty)

        (activity as MainActivity).displayMessageWithRefreshBtn(
            error.message ?: getString(R.string.sources_unknown_error)
        ) { viewModel.onRefreshSelected() }
    }

    private fun handleScreenDisplay(isEmptyList: Boolean) {
        when (isEmptyList) {
            true -> displayNotificationScreen()
            false -> displaySourcesScreen()
        }
    }

    private fun displayNotificationScreen() {
        setScreenVisibilityState(View.GONE, View.VISIBLE)
    }

    private fun displaySourcesScreen() {
        setScreenVisibilityState(View.VISIBLE, View.GONE)
    }

    private fun setScreenVisibilityState(stateRecyclerView: Int, stateErrorScreen: Int) {
        binding.sourcesRecyclerView.visibility = stateRecyclerView
        binding.sourcesErrorsScreen.visibility = stateErrorScreen
    }

    private fun setUpOnRefreshListener() {
        binding.swipeSourceRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshSelected()
        }
    }

    private fun isSwipeRefreshing(isEnabled: Boolean) {
        binding.swipeSourceRefreshLayout.isRefreshing = isEnabled
    }

    private fun openArticlesFragment(sourceDomain: SourceDomain) {
        (parentFragment as NewsPagerContainer).openArticlesFragment(sourceDomain)
    }

    private fun setUpOnItemClick(sourceDomain: SourceDomain) {
        openArticlesFragment(sourceDomain)
    }

    companion object {
        fun newInstance() = SourcesFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter = null
    }
}
