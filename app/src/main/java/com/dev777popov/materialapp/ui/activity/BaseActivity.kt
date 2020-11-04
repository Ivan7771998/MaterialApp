package com.dev777popov.materialapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev777popov.materialapp.App
import com.dev777popov.materialapp.R


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getMyTheme())
    }

    fun changeTheme(id: Int) {
        setTheme(id)
        App.instance.getSP().setTheme(id)
        recreate()
    }

    private fun getMyTheme(): Int {
        return if (App.instance.getSP().getTheme() == 0) {
            R.style.BaseAppTheme
        }else {
            App.instance.getSP().getTheme()
        }
    }
}