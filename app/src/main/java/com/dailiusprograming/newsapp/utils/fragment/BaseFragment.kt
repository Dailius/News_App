package com.dailiusprograming.newsapp.utils.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dailiusprograming.newsapp.utils.activity.HandleBack

abstract class BaseFragment : Fragment(), HandleBack {
    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onBackPressed() = false
}
