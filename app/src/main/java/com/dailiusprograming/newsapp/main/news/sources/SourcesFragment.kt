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
        binding.swipeSourceRefreshLayout.isRefreshing = true
        setUpRecyclerView()
        setUpViewModelObserver()
        setUpOnRefreshListener()
        viewModel.onRefreshSelected()
    }

    private fun setUpRecyclerView() {
        binding.sourcesRecyclerView.apply {
            recyclerAdapter = SourcesAdapter { source -> setUpOnItemClick(source) }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setUpViewModelObserver() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, { result ->
            binding.swipeSourceRefreshLayout.isRefreshing = result
        })
        viewModel.sourceList.observe(viewLifecycleOwner, { list ->
            displaySourcesScreen()
            recyclerAdapter?.submitList(list)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            if (recyclerAdapter?.currentList?.isEmpty() == true) displayErrorScreen()

            (activity as MainActivity).displayMessageWithRefreshBtn(
                error.message ?: getString(R.string.feature_sources_unknown_error)
            ) { viewModel.onRefreshSelected() }
        })
    }

    private fun setScreenVisibilityState(stateRecyclerView: Int, stateErrorScreen: Int) {
        binding.sourcesRecyclerView.visibility = stateRecyclerView
        binding.sourcesErrorsScreen.visibility = stateErrorScreen
    }

    private fun displaySourcesScreen() {
        setScreenVisibilityState(View.VISIBLE, View.GONE)
    }

    private fun displayErrorScreen() {
        setScreenVisibilityState(View.GONE, View.VISIBLE)
    }

    private fun setUpOnRefreshListener() {
        binding.swipeSourceRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshSelected()
        }
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
