package com.carrot.presentation.view.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.model.DiaryHeader
import com.carrot.presentation.model.mapper.toView
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
    private val _diaryList = MutableStateFlow<List<DiaryHeader>>(emptyList())
    val diaryList = _diaryList.asStateFlow()

    private val token = sharedPreferencesService.cookie

    init {
        println("프로필 $token")
    }

    fun loadDiaryTitleList() {
        viewModelScope.launch {
            val result = api.getDiary(
                authorization = sharedPreferencesService.cookie ?: ""
            )
            if (result.isSuccessful && result.code() == 200) {
                result.body().let { diaryListDTO ->
                    _diaryList.value = diaryListDTO!!.toView()
                }
            }
        }
    }
}
