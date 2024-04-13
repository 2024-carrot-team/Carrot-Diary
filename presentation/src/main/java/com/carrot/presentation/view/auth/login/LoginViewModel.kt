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
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel() {
    private val _isLogin = MutableStateFlow<Int>(0)
    val isLogin = _isLogin.asStateFlow()

    var cookie: String = ""

    val emailPattern =
        "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"

    private val pwdPattern =
        "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,16}$"


    fun login(id: String, password: String) {
        viewModelScope.launch {
            val result = api.postLogin(
                LoginRequest(id, password)
            )
            if (result.isSuccessful) {
                sharedPreferencesService.cookie =
                    result.headers().values("Set-Cookie")[0]

                _isLogin.value = 1

            } else {
                _isLogin.value = 2
            }
        }
    }

    fun extractTokenFromCookie(cookie: String): String? {
        // "Authorization=Bearer%20{token}; Path=/" 형태의 쿠키에서 토큰 부분만 추출
        val tokenPrefix = "Authorization=Bearer%20"
        val tokenStartIndex = cookie.indexOf(tokenPrefix)

        if (tokenStartIndex == -1) {
            return null // 쿠키에서 해당 문자열을 찾을 수 없는 경우
        }

        val tokenStart = tokenStartIndex + tokenPrefix.length
        val tokenEnd = cookie.indexOf(';', tokenStart).let { if (it == -1) cookie.length else it }
        val token = cookie.substring(tokenStart, tokenEnd)

        // URL 디코딩
        return java.net.URLDecoder.decode(token, "UTF-8")
    }

    fun addAuthorizationHeader(cookie: String): String {
        val token = extractTokenFromCookie(cookie)
        return if (token != null) "Bearer $token" else ""
    }

    fun isEmail(id: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(id).matches()
    fun isPassword(pwd: String): Boolean = Pattern.matches(pwdPattern, pwd)

}