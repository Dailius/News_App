package com.dailiusprograming.newsapp.utils.fragment

import androidx.fragment.app.Fragment
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.main.pager.MainPagerAdapter

fun Fragment.openFragment(
    fragment: Fragment,
    addToBackStack: Boolean,
    pageConstFromMainPagerAdapter: Int = MainPagerAdapter.NEWS_PAGE
) {
    var fragmentLayoutId: Int = R.id.newsFragmentContainer
    if (pageConstFromMainPagerAdapter == MainPagerAdapter.FAVORITES_PAGE) {
        fragmentLayoutId = R.id.favoriteFragmentContainer
    }
    val transaction = childFragmentManager.beginTransaction().replace(fragmentLayoutId, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}
