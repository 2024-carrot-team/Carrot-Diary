package com.carrot.carrotdiary.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carrot.carrotdiary.databinding.ActivityLoginBinding
import com.carrot.carrotdiary.view.main.MainActivity
import com.carrot.carrotdiary.view.main.signup.SignUpActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())

        binding.buttonLoginLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignUpLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

}