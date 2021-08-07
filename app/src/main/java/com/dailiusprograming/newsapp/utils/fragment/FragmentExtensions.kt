package com.dailiusprograming.newsapp.utils.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.main.pager.MainPagerAdapter

fun Fragment.openFragment(
    fragment: Fragment,
    addToBackStack: Boolean,
    pageConstFromMainPagerAdapter: Int = MainPagerAdapter.NEWS_PAGE,
) {
    val layoutRes = when (pageConstFromMainPagerAdapter) {
        MainPagerAdapter.FAVORITES_PAGE -> {
            R.id.favoriteFragmentContainer
        }
        else -> {
            R.id.newsFragmentContainer
        }
    }

    val transaction = childFragmentManager.beginTransaction().replace(
        layoutRes,
        fragment,
    )
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView
    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * 4)
}
