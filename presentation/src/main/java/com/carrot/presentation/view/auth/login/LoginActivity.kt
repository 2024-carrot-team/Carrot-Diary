package com.carrot.presentation.view.auth.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.databinding.ActivityLoginBinding
import com.carrot.presentation.R
import com.carrot.presentation.view.auth.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        initLiveData()
        val action = supportActionBar
        action?.hide()
        initListener()


    }

    private fun initListener() {
        binding.buttonLoginLogin.setOnClickListener {
            val email = binding.editTextLoginIdLoginActivity.text.toString()
            val pass = binding.editTextPasswordLoginActivity.text.toString()
            viewModel.login(
                email, pass
            )
        }
        binding.textSignUpLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLoginKakao.setOnClickListener {
            toastMassage(getString(R.string.developing))

        }

        binding.textFindIdLogin.setOnClickListener {
            toastMassage(getString(R.string.developing))

        }
        binding.textFindPassLogin.setOnClickListener {
            toastMassage(getString(R.string.developing))
        }

        binding.editTextLoginIdLoginActivity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val id = binding.editTextLoginIdLoginActivity.text.toString().trim()
                binding.textViewEmailLabelLoginActivity.isVisible = !viewModel.isEmail(id)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.editTextPasswordLoginActivity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val password = binding.editTextPasswordLoginActivity.text.toString().trim()
                val len = password.length
                when {
                    len < 8 || len > 16 -> {
                        binding.textViewPasswordLabelLoginActivity.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.textViewPasswordLabelLoginActivity.visibility = View.INVISIBLE
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })



    }

    private fun initLiveData() {
        lifecycleScope.launch {
            viewModel.isLogin.collect { isLogin ->
                if (isLogin == 2) {
                    toastMassage("로그인 실패")
                } else if (isLogin == 1) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun toastMassage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

