package com.dev777popov.materialapp.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dev777popov.materialapp.R

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun setRedTheme(view: View) = changeTheme(R.style.RedAppTheme)
    fun setPurpleTheme(view: View) = changeTheme(R.style.PurpleAppTheme)
    fun setOrangeTheme(view: View) = changeTheme(R.style.OrangeAppTheme)
    fun setDefaultTheme(view: View) = changeTheme(R.style.BaseAppTheme)

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent())
        super.onBackPressed()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }
}