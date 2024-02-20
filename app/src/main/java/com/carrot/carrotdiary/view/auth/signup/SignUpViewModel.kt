package com.carrot.carrotdiary.view.auth.signup

import androidx.lifecycle.ViewModel
import com.carrot.carrotdiary.retrofit.RetrofitInstance

class SignUpViewModel : ViewModel() {

    private val retrofit = RetrofitInstance.getInstance()
    fun signUp(id: String, password: String) {
        retrofit
    }
}