package com.carrot.presentation.view.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
    private var isAgree: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonSignUpSignUpActivity.setOnClickListener {
            val id = binding.editTextLoginIdSignUpActivity.text.toString()
            val password = binding.editTextPasswordSignUpActivity.text.toString()
            val passwordCheck = binding.editTextPasswordCheckSignUpActivity.text.toString()
            val nickname = binding.editTextNickNameSignUpActivity.text.toString()
            val birthDate = binding.editTextBirthDateSignUpActivity.text.toString()

            if (id.isNullOrBlank() || id.isNullOrEmpty() || nickname.isNullOrEmpty() || birthDate.isNullOrEmpty()) {
                Toast.makeText(this, "입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (!password.equals(passwordCheck)) {
                Toast.makeText(this, "비밀번호 확인해주세요.", Toast.LENGTH_SHORT).show()
            } else if (isAgree) {
                viewModel.signUp(id, password, nickname, birthDate)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "개인정보처리방침 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textTermsSignUpActivity.setOnClickListener {
            // 개인정보 처리방침 바로가기
            val intent = Intent(this@SignUpActivity, WebViewActivity::class.java)
            startActivity(intent)
            isAgree = when (isAgree) {
                true -> {
                    false
                }

                else -> {
                    true
                }
            }
            binding.cbTermsSignUpActivity.isChecked = isAgree
        }
        initListener()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> { // 뒤로가기버튼
                finish()
                return true;
            }

            else -> {
            }

        }

        return super.onOptionsItemSelected(item)
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