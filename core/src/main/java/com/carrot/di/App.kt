package com.carrot.di
import android.app.Application

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