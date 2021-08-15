package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentFavoritesBinding
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private var recyclerAdapter: FavoritesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.favoritesRecyclerView.apply {
            recyclerAdapter = FavoritesAdapter(::onFavoritesItemClick)
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun submitArticleList(list: List<ArticleDomain>) {
        recyclerAdapter?.submitList(list)
        binding.favoritesRecyclerView.adapter = recyclerAdapter
    }

    private fun onFavoritesItemClick(articleDomain: ArticleDomain) {
        openDetailsFragment(articleDomain)
    }

    private fun openDetailsFragment(articleDomain: ArticleDomain) {
        (parentFragment as FavoritesPagerContainer).openDetailsFragment(articleDomain)
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter = null
    }
}
