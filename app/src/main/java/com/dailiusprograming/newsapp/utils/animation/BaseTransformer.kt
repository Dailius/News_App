package com.dailiusprograming.newsapp.utils.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2

abstract class BaseTransformer : ViewPager2.PageTransformer {
    /**
     * Called each [.transformPage].
     *
     * @param view
     * @param position
     */
    abstract fun onTransform(view: View?, position: Float)
    override fun transformPage(view: View, position: Float) {
        onPreTransform(view, position)
        onTransform(view, position)
        onPostTransform(view, position)
    }

    /**
     * If the position offset of a fragment is less than negative one or greater than one, returning true will set the
     * visibility of the fragment to [android.view.View.GONE]. Returning false will force the fragment to [android.view.View.VISIBLE].
     *
     * @return
     */
    private fun hideOffscreenPages(): Boolean {
        return true
    }

    /**
     * Indicates if the default animations of the view pager should be used.
     *
     * @return
     */
    private val isPagingEnabled: Boolean
        get() = false

    /**
     * Called each [.transformPage] before {[.onTransform] is called.
     *
     * @param view
     * @param position
     */
    private fun onPreTransform(view: View, position: Float) {
        val width: Int = view.width
        view.rotationX = 0F
        view.rotationY = 0F
        view.rotation = 0F
        view.scaleX = 1F
        view.scaleY = 1F
        view.pivotX = 0F
        view.pivotY = 0F
        view.translationY = 0F
        view.translationX = if (isPagingEnabled) 0f else -width * position
        if (hideOffscreenPages()) {
            view.alpha = if (position <= -1f || position >= 1f) 0f else 1f
        } else {
            view.alpha = 1f
        }
    }

    /**
     * Called each [.transformPage] call after [.onTransform] is finished.
     *
     * @param view
     * @param position
     */
    private fun onPostTransform(view: View?, position: Float) {}
}
