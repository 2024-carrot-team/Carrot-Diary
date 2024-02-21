package com.carrot.carrotdiary.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.carrot.carrotdiary.R
import com.carrot.carrotdiary.databinding.ActivityMainBinding
import com.carrot.carrotdiary.view.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // app themes 색상 참조하지 않음.
        val bottomNavigationView = binding.navigationMain
        bottomNavigationView.itemIconTintList = null

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host)?.findNavController()
        navController?.let {
            bottomNavigationView.setupWithNavController(it)
        }
        binding.buttonSettingMain.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        val action = supportActionBar
        action?.hide()
    }
}