package com.carrot.presentation.view.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.model.PostUser
import com.carrot.data.remote.api.LoginApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val api: LoginApiService,

    ) : ViewModel() {
    private val dateTimeFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())

    private val _signUpResultCode = MutableStateFlow<Int>(0)

    val signUpResultCode = _signUpResultCode.asStateFlow()

    val emailPattern =
        "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"

    private val pwdPattern =
        "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,16}$"

    //    val pattern = Pattern.matches(pwdPattern, pwd)
    fun signUp(email: String, password: String, nickname: String, birthDate: String) {
        viewModelScope.launch {
            val result = api.postRegister(
                PostUser(
                    email = email,
                    password = password,
                    nickname = nickname,
                    birthDayTime = "1990-01-01T00:00:00",
                    role = "USER"
                )
            )
            _signUpResultCode.value = result.code()
            println("api회원가입 issuccesful ${result.isSuccessful}")
            println("api회원가입 result.body() ${result.body()}")
            println("api회원가입 result.code() ${result.code()}")
            println("api회원가입 result.hashCode() ${result.hashCode()}")
        }
    }

    fun getNowTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return dateFormat.format(calendar.time)
    }

    //    fun isEmail(id: String): Boolean = Pattern.matches(emailPattern, id)
    fun isEmail(id: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(id).matches()

    fun isPassword(pwd: String): Boolean = Pattern.matches(pwdPattern, pwd)


    fun isPasswordEqual(pwd: String, pwdCheck: String): Boolean = pwd == pwdCheck
}