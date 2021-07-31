package com.dailiusprograming.newsapp.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentFavoritesContainerBinding
import com.dailiusprograming.newsapp.main.news.details.DetailsFragment
import com.dailiusprograming.newsapp.main.pager.MainPagerAdapter
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.fragment.openFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragmentContainer : BaseFragment(), FavoritesPagerContainer {
    override val layoutRes get() = R.layout.fragment_favorites_container
    private var _binding: FragmentFavoritesContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openFavoritesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun openFavoritesFragment() {
        openFragment(
            FavoritesFragment.newInstance(),
            addToBackStack = false,
            pageConstFromMainPagerAdapter = MainPagerAdapter.FAVORITES_PAGE
        )
    }

    override fun openDetailsFragment() {
        openFragment(
            DetailsFragment.newInstance(),
            addToBackStack = true,
            pageConstFromMainPagerAdapter = MainPagerAdapter.FAVORITES_PAGE,
            fragmentTag = FRAGMENT_TAG
        )
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

    companion object {
        const val FRAGMENT_TAG = "details"
        fun newInstance() = FavoritesFragmentContainer()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
