package com.carrot.carrotdiary.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carrot.carrotdiary.databinding.ActivityLoginBinding
import com.carrot.carrotdiary.view.main.MainActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())

        binding.btnLogin.setOnClickListener { v ->

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
    
}