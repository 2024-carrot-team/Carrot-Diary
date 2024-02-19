package com.carrot.carrotdiary.view.main.signup

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {


    fun login(id: String, password: String) {
        println("회원가입 $id $password")
    }
}