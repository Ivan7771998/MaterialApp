package com.dev777popov.materialapp

import android.app.Application
import com.dev777popov.materialapp.tools.MySharedPreference

class App : Application() {

    private lateinit var pref: MySharedPreference

    override fun onCreate() {
        super.onCreate()
        instance = this
        pref = MySharedPreference(this)
    }

    fun getSP() = pref

    companion object {
        lateinit var instance: App
            private set
    }

}