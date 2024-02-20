package com.carrot.carrotdiary.view.auth.login

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun login(id: String, password: String) {
        println("로그인 $id $password")
    }
}