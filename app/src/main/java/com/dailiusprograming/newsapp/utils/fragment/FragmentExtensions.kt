package com.dailiusprograming.newsapp.utils.fragment

import androidx.fragment.app.Fragment
import com.dailiusprograming.newsapp.R

fun Fragment.openFragment(fragment: Fragment, addToBackStack: Boolean){
    // TODO replace R.id.container with correct one
    val transaction = childFragmentManager.beginTransaction().replace(R.id.container, fragment)
    if (addToBackStack){
        transaction.addToBackStack(null)
    }
    transaction.commit()
}
