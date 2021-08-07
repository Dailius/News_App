package com.dailiusprograming.newsapp.main.pager

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentMainPagerBinding
import com.dailiusprograming.newsapp.utils.activity.HandleBack
import com.dailiusprograming.newsapp.utils.animation.ZoomOutPageTransformer
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment
import com.dailiusprograming.newsapp.utils.fragment.reduceDragSensitivity
import com.dailiusprograming.newsapp.utils.view.viewBinding

class MainPagerFragment : BaseFragment(R.layout.fragment_main_pager) {
    private lateinit var adapter: MainPagerAdapter
    private val binding by viewBinding(FragmentMainPagerBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MainPagerAdapter(fragmentManager = childFragmentManager, lifecycle = lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBottomNavigationView()
        setUpViewPager()
    }

    private fun setUpBottomNavigationView() {
        binding.apply {
            bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.newsMenuItem -> {
                        mainViewPager.currentItem = MainPagerAdapter.NEWS_PAGE
                        true
                    }
                    R.id.favoriteMenuItem -> {
                        mainViewPager.currentItem = MainPagerAdapter.FAVORITES_PAGE
                        true
                    }
                    R.id.aboutMenuItem -> {
                        mainViewPager.currentItem = MainPagerAdapter.ABOUT_PAGE
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setUpViewPager() {
        binding.apply {
            mainViewPager.apply {
                adapter = this@MainPagerFragment.adapter
                reduceDragSensitivity()
                setPageTransformer(ZoomOutPageTransformer( ))
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        bottomNavigationView.menu.getItem(position).isChecked = true
                    }
                })
                offscreenPageLimit = 2
            }
        }
    }

    override fun onBackPressed(): Boolean {
        val viewPagerItem = binding.mainViewPager.currentItem
        val isNewsPageDisplayed = (viewPagerItem == MainPagerAdapter.NEWS_PAGE)
        val isFavoritesPageDisplayed = (viewPagerItem == MainPagerAdapter.FAVORITES_PAGE)
        val fragment = childFragmentManager.findFragmentByTag(
            resources.getString(R.string.main_pager_fragment_tag, viewPagerItem.toString())
        )
        val isHandleBack = fragment is HandleBack && fragment.onBackPressed()
        return when {
            isNewsPageDisplayed -> {
                isHandleBack
            }
            isFavoritesPageDisplayed -> {
                if (!isHandleBack) {
                    binding.mainViewPager.currentItem = MainPagerAdapter.NEWS_PAGE
                }
                true
            }
            !isNewsPageDisplayed -> {
                binding.mainViewPager.currentItem = MainPagerAdapter.NEWS_PAGE
                true
            }
            else -> false
        }
    }

    companion object {
        fun newInstance() = MainPagerFragment()
    }
}
