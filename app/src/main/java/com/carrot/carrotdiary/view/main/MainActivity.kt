package com.carrot.carrotdiary.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carrot.carrotdiary.databinding.ActivityMainBinding
import com.carrot.carrotdiary.view.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        // 좋아 너무 좋아
        binding.buttonSettingMain.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        val action = supportActionBar
        action?.hide()
    }
}