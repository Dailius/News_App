package com.dailiusprograming.newsapp.main.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.FragmentMainPagerBinding
import com.dailiusprograming.newsapp.utils.activity.HandleBack
import com.dailiusprograming.newsapp.utils.fragment.BaseFragment

class MainPagerFragment : BaseFragment() {
    override val layoutRes get() = R.layout.fragment_main_pager
    private lateinit var adapter: MainPagerAdapter
    private var _binding: FragmentMainPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MainPagerAdapter(fragmentManager = childFragmentManager, lifecycle = lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPagerBinding.inflate(inflater, container, false)
        return binding.root
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
        return when {
            viewPagerItem != MainPagerAdapter.NEWS_PAGE -> {
                binding.mainViewPager.currentItem = MainPagerAdapter.NEWS_PAGE
                true
            }
            viewPagerItem == MainPagerAdapter.NEWS_PAGE -> {
                val fragment = childFragmentManager.findFragmentByTag(
                    "android:switcher:" + R.id.mainViewPager + ":" + viewPagerItem
                )
                fragment is HandleBack && fragment.onBackPressed()
            }
            else -> false
        }
    }

    companion object {
        fun newInstance() = MainPagerFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
