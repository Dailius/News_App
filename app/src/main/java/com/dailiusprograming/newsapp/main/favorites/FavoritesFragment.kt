package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import android.view.View
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentFavoritesBinding
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.view.viewBinding

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener { openDetailsFragment() }
    }

    private fun openDetailsFragment() {
        (parentFragment as FavoritesPagerContainer).openDetailsFragment()
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
