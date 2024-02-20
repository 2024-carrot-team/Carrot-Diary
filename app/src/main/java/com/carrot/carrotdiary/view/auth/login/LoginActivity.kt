package com.carrot.carrotdiary.view.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.carrot.carrotdiary.databinding.ActivityLoginBinding
import com.carrot.carrotdiary.view.auth.signup.SignUpActivity
import com.carrot.carrotdiary.view.main.MainActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())

        binding.buttonLoginLogin.setOnClickListener {
            val id = binding.editTextLoginIdLogin.text.toString()
            val password = binding.editTextPasswordLogin.text.toString()
            viewModel.login(id, password)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignUpLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

}