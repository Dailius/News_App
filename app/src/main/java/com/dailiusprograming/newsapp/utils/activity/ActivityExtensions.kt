package com.dailiusprograming.newsapp.utils.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dailiusprograming.newsapp.R

fun FragmentActivity.openFragment(fragment: Fragment, addToBackStack: Boolean) {
    val transaction =
        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainer, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}
