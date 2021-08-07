package com.dailiusprograming.newsapp.main.news.sources

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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
    private val recyclerAdapter = SourcesAdapter { source -> setUpOnItemClick(source) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModelObserver()
        setUpOnRefreshListener()
        viewModel.onRefreshSelected()
    }

    private fun setUpRecyclerView() {
        binding.sourceRecyclerView.apply {
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
            binding.sourceRecyclerView.isVisible = true
            recyclerAdapter.submitList(list)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            (activity as MainActivity).displayMessageWithRefreshBtn(
                error.message ?: getString(R.string.feature_sources_unknown_error)
            ) { viewModel.onRefreshSelected() }
        })
    }

    private fun setUpOnRefreshListener() {
        binding.swipeSourceRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshSelected()
        }
    }

    private fun openArticlesFragment(args: String) {
        (parentFragment as NewsPagerContainer).openArticlesFragment(args)
    }

    private fun setUpOnItemClick(sourceDomain: SourceDomain) {
        val args: String = sourceDomain.id
        openArticlesFragment(args)
    }

    companion object {
        fun newInstance() = SourcesFragment()
    }
}
