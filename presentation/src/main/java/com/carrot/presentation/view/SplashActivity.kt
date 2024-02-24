package com.carrot.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carrot.presentation.R
import com.carrot.presentation.view.auth.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, LoginActivity::class.java)
        lifecycleScope.launch {
            delay(1000)
            startActivity(intent)
            finish()
        }
    }
}