package com.dailiusprograming.newsapp.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dailiusprograming.newsapp.databinding.ActivityTutorialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
