package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentFavoritesBinding
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment

class FavoritesFragment : BaseFragment() {
    override val layoutRes get() = R.layout.fragment_favorites
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
