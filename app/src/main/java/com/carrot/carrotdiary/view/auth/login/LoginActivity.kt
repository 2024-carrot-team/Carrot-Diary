<<<<<<<< HEAD:app/src/main/java/com/carrot/carrotdiary/view/auth/login/LoginActivity.kt
package com.carrot.carrotdiary.view.auth.login
========
package com.carrot.presentation.view
>>>>>>>> origin/feature/android-dev:presentation/src/main/java/com/carrot/presentation/view/LoginActivity.kt

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
<<<<<<<< HEAD:app/src/main/java/com/carrot/carrotdiary/view/auth/login/LoginActivity.kt
import com.carrot.carrotdiary.databinding.ActivityLoginBinding
import com.carrot.carrotdiary.view.auth.signup.SignUpActivity
import com.carrot.carrotdiary.view.main.MainActivity
========
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.view.main.signup.SignUpActivity
import com.carrot.presentation.databinding.ActivityLoginBinding
>>>>>>>> origin/feature/android-dev:presentation/src/main/java/com/carrot/presentation/view/LoginActivity.kt


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