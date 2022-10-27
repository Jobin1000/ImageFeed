package com.android.imagefeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.imagefeed.ui.main.ImageFeedFragment

/**
 * First activity to be launched from launcher. Host Activity for the fragment
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ImageFeedFragment.newInstance())
                .commitNow()
        }
    }
}