package com.dev777popov.materialapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dev777popov.materialapp.R
import com.dev777popov.materialapp.tools.Const
import com.dev777popov.materialapp.ui.fragments.PhotosFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(PhotosFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).commit()
    }
}