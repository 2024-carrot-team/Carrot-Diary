package com.carrot.presentation.view.main.profile

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
            val result = api.getDiary()
            _diaryList.value = Dummy.diaryList
            println("api요청 issuccesful ${result.isSuccessful}")
            println("api요청 result.body() ${result.body()}")
        }
    }
}
