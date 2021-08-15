package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentFavoritesBinding
import com.dailiusprograming.newsapp.main.news.articles.data.model.ArticleDomain
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding
import java.util.*

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickTextView()
    }

    private fun onClickTextView() {
        val articleDomain = ArticleDomain(
            "",
            "sourceId",
            "source Name",
            "Author",
            "Title",
            "Description",
            "ImageUrl",
            Date(),
            "Content",
            false
        )
//        binding.textView.setOnClickListener { openDetailsFragment(articleDomain) }
    }

    private fun openDetailsFragment(articleDomain: ArticleDomain) {
        (parentFragment as FavoritesPagerContainer).openDetailsFragment(articleDomain)
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
