package com.carrot.presentation.view.auth.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.databinding.ActivityLoginBinding
import com.carrot.data.model.PostUser
import com.carrot.presentation.view.auth.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    //    @Inject
//    lateinit var api: LoginApiService
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        initLiveData()

        binding.buttonLoginLogin.setOnClickListener {
            val email = binding.editTextLoginIdLogin.text.toString()
            val pass = binding.editTextPasswordLogin.text.toString()
            viewModel.login(
                email, pass
            )
        }
        binding.buttonSignUpLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initLiveData() {
        lifecycleScope.launch {
            viewModel.isLogin.collect { isLogin ->
                if (isLogin == 2) {
                    toastMassage("로그인 실패")
                } else if (isLogin == 1) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    println("쿠키 입력${isLogin}")
//                    editor.putString("token", isLogin)
//                    editor.apply()
                    startActivity(intent)
                    toastMassage("로그인 성공")
                    finish()
                }
            }
        }
    }

    private fun toastMassage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

