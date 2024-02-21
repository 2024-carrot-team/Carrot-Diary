package com.carrot.carrotdiary.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {
    companion object {
        private lateinit var applicaton: App
        fun getInstance(): App = applicaton
    }

    override fun onCreate() {
        super.onCreate()
        applicaton = this
    }
}