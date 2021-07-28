package com.dailiusprograming.newsapp.utils.fragment

import androidx.fragment.app.Fragment
import com.dailiusprograming.newsapp.R

fun Fragment.openFragment(fragment: Fragment, addToBackStack: Boolean) {
    val transaction = childFragmentManager.beginTransaction().replace(R.id.newsFragmentContainer, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}
