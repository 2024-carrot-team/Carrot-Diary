package com.carrot.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val COOKIE_KEY = "Cookie"
    }

    var cookie: String?
        get() = sharedPreferences.getString(COOKIE_KEY, null)
        set(value) {
            sharedPreferences.edit().putString(COOKIE_KEY, value).apply()
        }

}