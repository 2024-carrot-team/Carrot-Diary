package com.carrot.presentation.view.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.common.Dummy
import com.carrot.presentation.model.Diary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel(
) {
    private val _diaryList = MutableStateFlow<List<Diary>>(emptyList())
    val diaryList = _diaryList.asStateFlow()

    private val token = sharedPreferencesService.cookie

    init {
        println("프로필 $token")
        dummyDiary()
    }

    fun dummyDiary() {
        viewModelScope.launch {
            val result = api.getDiary(
                authorization = sharedPreferencesService.cookie ?: ""
            )
            _diaryList.value = Dummy.diaryList
            println("토큰 확인 ${addAuthorizationHeader(sharedPreferencesService.cookie ?: "")}")
            println("api요청 sharedPreferencesService.cookie ${sharedPreferencesService.cookie}")
            println("api요청 issuccesful ${result.isSuccessful}")
            println("api요청 result.body() ${ sharedPreferencesService.cookie }")
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

        return if (token != null) {
            Log.d("testToken", token)
            "Bearer $token"
        } else ""
    }
}
