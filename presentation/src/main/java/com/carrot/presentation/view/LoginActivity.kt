package com.carrot.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.view.main.signup.SignUpActivity
import com.carrot.presentation.databinding.ActivityLoginBinding
import com.carrot.data.model.PostUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var api: LoginApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())

        binding.buttonLoginLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.buttonSignUpLogin.setOnClickListener {


            val email = binding.editTextLoginIdLogin.text.toString()
            val pass = binding.editTextLoginIdLogin.text.toString()
            val nickName = binding.editTextNickNameLogin.text.toString()
            val birth = binding.editTextBirthDateLogin.text.toString()

            val t = PostUser(
                email = email,
                password = "Password1!",
                nickname = "himaydu",
                birthDayTime = "1990-01-01T00:00:00",
                role = "USER"
            )

            lifecycleScope.launch {
                api.postRegister(t)
                    ?.body().apply {

                        Toast.makeText(this@LoginActivity, this.toString(), Toast.LENGTH_SHORT)
                            .show()

                        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                        startActivity(intent)

                    }


            }


        }


    }

}

