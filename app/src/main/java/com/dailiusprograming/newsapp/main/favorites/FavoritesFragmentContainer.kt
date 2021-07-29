package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentFavoritesContainerBinding
import com.dailiusprograming.newsapp.databinding.FragmentNewsContainerBinding
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragmentContainer : BaseFragment() {
    override val layoutRes get() = R.layout.fragment_favorites_container
    private var _binding: FragmentFavoritesContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoritesContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        fun newInstance() = FavoritesFragmentContainer()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

