package com.dailiusprograming.newsapp.utils.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dailiusprograming.newsapp.R
import com.dailiusprograming.newsapp.main.MainActivity
import com.google.android.material.snackbar.Snackbar

fun FragmentActivity.openFragment(fragment: Fragment, addToBackStack: Boolean) {
    val transaction =
        supportFragmentManager.beginTransaction().replace(
            R.id.mainFragmentContainer, fragment
        )
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

fun MainActivity.displayMessage(message: String) {
    Snackbar.make(
        findViewById(R.id.bottomNavigationView),
        message,
        Snackbar.LENGTH_LONG
    ).apply {
        anchorView = findViewById(R.id.bottomNavigationView)
        show()
    }
}

fun MainActivity.displayMessageWithRefreshBtn(
    message: String,
    onClick: View.OnClickListener
) {
    Snackbar.make(
        findViewById(R.id.bottomNavigationView),
        message,
        Snackbar.LENGTH_LONG
    ).apply {
        anchorView = findViewById(R.id.bottomNavigationView)
        setActionTextColor(context.getColor(R.color.selected))
        setAction(getString(R.string.message_button_retry), onClick)
        show()
    }
}


