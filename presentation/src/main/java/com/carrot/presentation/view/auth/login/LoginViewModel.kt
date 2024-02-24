package com.carrot.presentation.view.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.model.LoginRequest
import com.carrot.data.remote.api.LoginApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel() {
    private val _isLogin = MutableStateFlow<Int>(0)
    val isLogin = _isLogin.asStateFlow()

    var cookie: String = ""

    fun login(id: String, password: String) {
        viewModelScope.launch {
            val result = api.postLogin(
                LoginRequest(id, password)
            )

            if (result.isSuccessful) {
                _isLogin.value = 1
                sharedPreferencesService.cookie = result.headers().values("Set-Cookie").first()
                println("로그인 뷰모델 쿠키 값 size ${result.headers().values("Set-Cookie").size}")
                println("로그인 뷰모델 쿠키 값 전체 ${result.headers().values("Set-Cookie")[0]}")
                println("로그인 뷰모델 쿠키 값 전체 ${result.headers().values("Set-Cookie")}")
                println("로그인 뷰모델 쿠키 값 입력 ${sharedPreferencesService.cookie}")
            } else {
                _isLogin.value = 2
            }
        }
    }
//    fun login(postUser: PostUser) {
//        viewModelScope.launch {
//            val result = api.postLogin(postUser)
//            println("api요청 issuccesful ${result.isSuccessful}")
//            println("api요청 result.body() ${result.body()}")
//            if (result.isSuccessful) {
//                _isLogin.value = true
//            }
//        }
//    }
}