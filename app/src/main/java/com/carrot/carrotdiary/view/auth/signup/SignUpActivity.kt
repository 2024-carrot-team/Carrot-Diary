<<<<<<<< HEAD:app/src/main/java/com/carrot/carrotdiary/view/auth/signup/SignUpActivity.kt
package com.carrot.carrotdiary.view.auth.signup
========
package com.carrot.presentation.view.main.signup
>>>>>>>> origin/feature/android-dev:presentation/src/main/java/com/carrot/presentation/view/main/signup/SignUpActivity.kt

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.carrot.presentation.databinding.ActivitySignUpBinding
import com.carrot.presentation.view.main.MainActivity

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
            viewModel.signUp(id, password)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}