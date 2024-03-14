package com.carrot.presentation.view.main.diary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.model.DailySeqDTO
import com.carrot.data.model.DiaryDetailDTO
import com.carrot.data.remote.api.LoginApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val api: LoginApiService,
    private val sharedPreferencesService: SharedPreferencesService,
) : ViewModel() {


    private val token = sharedPreferencesService.cookie


    /* 1 대 n 관계 */
    // 일기장의 일기들
    private val _postDiary = MutableStateFlow<DailySeqDTO?>(null)
    val postDiary = _postDiary.asStateFlow()

    // 일기의 사건과 이미지
    private val _dailyDetail = MutableStateFlow<DiaryDetailDTO?>(null)
    val dailyDetail = _postDiary.asStateFlow()

    private val diaryDetailsMap = mutableMapOf<Long, ArrayList<DiaryDetailDTO?>>()


    private val _seq = MutableStateFlow<String>("")
    val seq = _seq.asStateFlow()


    // 2. all/postDiaryId
    fun getPostDiary(postDiaryId: String) {
        viewModelScope.launch {
            val result = api.getPostDiary(
                authorization = sharedPreferencesService.cookie ?: "",
                postDiaryId = postDiaryId
            )
            if (result.isSuccessful && result.code() == 200 || result.code() == 201 || result.code() == 202 || result.code() == 203) {
                result.body().let { it ->
                    _postDiary.value = it

//                    key = daily id, value = DiaryDetailDTO 리스트
//                    for (i in 0 until _postDiary.value!!.data.size) {
//                        diaryDetailsMap[_postDiary.value!!.data[i].sequenceId] = ArrayList<DiaryDetailDTO?>()
//                    }


                    val sb = StringBuilder()
                    for (i in 0 until _postDiary.value!!.data.size) {
                        sb.append("${_postDiary.value!!.data[i].sequenceId}-")
                    }
                    _seq.value = sb.toString()
                }
            }
        }
    }

    // 3. detail/diaryId, adpater 내부에서 호출
    fun loadDetailDaily(diaryId: String): DiaryDetailDTO? {
        viewModelScope.launch {
            val result = api.getDailyDetail(
                authorization = sharedPreferencesService.cookie ?: "",
                diaryId = diaryId // 일기 id
            )
            if (result.isSuccessful && result.code() == 200 || result.code() == 201 || result.code() == 202 || result.code() == 203) {
                result.body().let { it ->
                    //dailyDetail.value = it
                    //TODO: 바인딩될 시점에 호출하고 저장할까? 그냥 한번에 다 호출하고 저장할까?
                    it

                }
            }else {
                Log.d("guen", "")
                 null
            }
        }
        return null
    }
}