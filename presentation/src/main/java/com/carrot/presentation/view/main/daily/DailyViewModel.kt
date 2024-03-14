package com.carrot.presentation.view.main.daily

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.model.DailySeqDTO
import com.carrot.data.model.DiaryDetailDTO
import com.carrot.data.model.MainDTO
import com.carrot.data.remote.api.LoginApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel(
) {

    private val token = sharedPreferencesService.cookie

    private val _mainDiary = MutableStateFlow<MainDTO?>(null)
    val mainDiary = _mainDiary.asStateFlow()

    private val _diarySeq = MutableStateFlow<DailySeqDTO?>(null)
    val diarySeq = _diarySeq.asStateFlow()

    private val _dailyDetail = MutableStateFlow<DiaryDetailDTO?>(null)
    val dailyDetail = _dailyDetail.asStateFlow()


    // 1. /main
    fun loadMainDaily() {
        viewModelScope.launch {
            val result = api.getMainPageDaily(
                authorization = sharedPreferencesService.cookie ?: "",
            )
            if (result.isSuccessful && result.code() == 200 || result.code() == 201 || result.code() == 202) {
                result.body().let { it ->
                    _mainDiary.value = it
                }
            }else {
                Log.d("DailyViewModel", result.code().toString())
            }
        }
    }


}