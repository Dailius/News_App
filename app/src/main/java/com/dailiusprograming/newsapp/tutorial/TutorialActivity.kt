package com.dailiusprograming.newsapp.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.databinding.ActivityTutorialBinding
import com.dailiusprograming.newsapp.main.MainActivity
import com.dailiusprograming.newsapp.tutorial.model.TutorialPage
import com.dailiusprograming.newsapp.utils.animation.ZoomOutPageTransformer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding
    private lateinit var pagerAdapter: TutorialPagerAdapter
    private val viewModel: TutorialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModelObserver()
    }

    private fun setUpViewModelObserver() {
        viewModel.pageList.observe(this, { list ->
            setUpViewPager(list)
        })
    }

    private fun setUpViewPager(list: List<TutorialPage>) {
        pagerAdapter = TutorialPagerAdapter(list)
        binding.viewPager.apply {
            adapter = pagerAdapter
            setPageTransformer(ZoomOutPageTransformer())
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.indicator.setViewPager2(binding.viewPager)
                    setButtonText(position)
                    setButtonClickListener()
                }
            })
        }
    }

    private fun setButtonText(position: Int) {
        if (position == pagerAdapter.itemCount - 1) {
            binding.button.text = getString(R.string.tutorial_button_done)
        } else {
            binding.button.text = getString(R.string.tutorial_button_next)
        }
    }

    private fun setButtonClickListener() {
        val pageOnScreen = binding.viewPager.currentItem
        binding.button.setOnClickListener {
            if (viewModel.openNextPage(pageOnScreen)) {
                binding.viewPager.currentItem += 1
            } else {
                openMainActivity()
            }
        }
    }

    private fun openMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        onTutorialFinished()
        finish()
    }

    private fun onTutorialFinished() {
        viewModel.onTutorialFinished(isTutorialDisplayed = true)
    }
}
