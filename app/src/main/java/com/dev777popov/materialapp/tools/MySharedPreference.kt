package com.dev777popov.materialapp.tools

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference constructor(context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE)

    private fun setPreferenceInt(key: String, value: Int) =
        preferences.edit().putInt(key, value).apply()

    private fun getPreferenceInt(key: String): Int = preferences.getInt(key, 0)

    fun setTheme(value: Int) = setPreferenceInt(PREF_THEME, value)
    fun getTheme() = getPreferenceInt(PREF_THEME)

    companion object {
        private const val PREF_THEME = "theme"
    }
}
