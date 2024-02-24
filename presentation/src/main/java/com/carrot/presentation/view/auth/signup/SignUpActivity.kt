package com.carrot.presentation.view.auth.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carrot.presentation.databinding.ActivitySignUpBinding
import com.carrot.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUpSignUpActivity.setOnClickListener {
            val id = binding.editTextLoginIdSignUpActivity.text.toString()
            val password = binding.editTextPasswordSignUpActivity.text.toString()
            val nickname = binding.editTextNickNameSignUpActivity.text.toString()
            val birthDate = binding.editTextBirthDateSignUpActivity.text.toString()
            viewModel.signUp(id, password, nickname, birthDate)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        initListener()
    }

    private fun initListener() {
        lifecycleScope.launch {
            viewModel.signUpResultCode.collect { code ->
                if (code == 201) {
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                    toastMassage()
                }
            }
        }
    }

    private fun toastMassage() {
        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
    }
}