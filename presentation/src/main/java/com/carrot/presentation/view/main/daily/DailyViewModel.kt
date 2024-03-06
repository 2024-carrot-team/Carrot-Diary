package com.carrot.presentation.view.main.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.model.mapper.toView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel(
) {

    private val token = sharedPreferencesService.cookie

    fun loadMainDaily(id: String) {
        viewModelScope.launch {
            val result = api.getMainPageDaily(
                authorization = sharedPreferencesService.cookie ?: "",
                postDiaryId = id,
            )
            if (result.isSuccessful && result.code() == 200) {
                result.body().let { dailyDTO ->


                }
            }
        }

    }
}