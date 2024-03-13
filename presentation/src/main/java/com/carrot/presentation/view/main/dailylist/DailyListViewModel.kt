package com.carrot.presentation.view.main.dailylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.model.DailyHeader
import com.carrot.presentation.model.mapper.toView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyListViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel() {


    private val token = sharedPreferencesService.cookie

    private val _dailyHeaderList = MutableStateFlow<List<DailyHeader>>(emptyList())
    val dailyHeaderList = _dailyHeaderList.asStateFlow()

    private val _diaryId = MutableStateFlow<Int>(0)

    val diaryId = _diaryId.asStateFlow()

    private val _newDailyId = MutableStateFlow<Int>(0)

    val newDailyId = _newDailyId.asStateFlow()
    fun loadDailyHeaderList(postId: Int) {
        viewModelScope.launch {
            val result = api.getDailyList(
                authorization = sharedPreferencesService.cookie ?: "",
                postId = postId.toString(),
            )
            if (result.isSuccessful && result.code() == 200) {
                result.body().let { dailyDTO ->
                    _dailyHeaderList.value = dailyDTO!!.toView()
                }
            }
        }
    }

    fun makeDaily(postId: Int) {
        viewModelScope.launch {
            val result = api.makeDaily(
                authorization = sharedPreferencesService.cookie ?: "",
                postId = postId.toString()
            )
            if (result.isSuccessful && result.code() == 200) {
                result.body().let { dailyIdDTO ->
                    _newDailyId.value = dailyIdDTO!!.toInt()
                }
            }
        }
    }
}