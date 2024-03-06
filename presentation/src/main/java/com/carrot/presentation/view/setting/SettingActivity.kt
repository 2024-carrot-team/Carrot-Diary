package com.carrot.presentation.view.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carrot.presentation.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val action = supportActionBar
        action?.hide()


    }
}