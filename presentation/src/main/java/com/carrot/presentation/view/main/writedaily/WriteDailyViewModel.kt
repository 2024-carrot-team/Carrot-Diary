package com.carrot.presentation.view.main.writedaily

import androidx.lifecycle.ViewModel
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.common.Dummy
import com.carrot.presentation.model.Accident
import com.carrot.presentation.model.Daily
import com.carrot.presentation.model.Diary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WriteDailyViewModel @Inject constructor(
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel() {


    private val dateTimeFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
    private val _diary = MutableStateFlow<Diary>(Dummy.diaryList.first())
    val diary = _diary.asStateFlow()

    private val _accidentList = MutableStateFlow<List<Accident>>(emptyList())
    val accidentList = _accidentList.asStateFlow()

    private val _imageList = MutableStateFlow<List<String>>(emptyList())

    val imageList = _imageList.asStateFlow()

    private val _date = MutableStateFlow<String>("${dateTimeFormatter.format(Date())}의 일기")
    val date = _date.asStateFlow()

    private val _accidentId = MutableStateFlow<Int>(0)

    private val accidentId = _accidentId.asStateFlow()

    private val token = sharedPreferencesService.cookie

    init {
//        _date.value = "${dateTimeFormatter.format(Date())}의 일기"
    }

    fun loadDiaryId(id: Int) {
        _diary.value = Dummy.diaryList[id]
    }

    fun setCookie() {
        println("뷰모델에 쿠키 도착 $token")
    }

    fun addImage(uri: String) {
        _imageList.value = _imageList.value + uri
    }

    fun clearImage() {
        _imageList.value = emptyList()
    }

    fun addAccident(content: String) {
        _accidentList.value = _accidentList.value + Accident(
            imageList = _imageList.value,
            id = _accidentId.value,
            date = _date.value,
            content = content
        )
        _accidentId.value += 1
    }

    fun addDaily() {
        val daily = Daily(
            id = 0,
            date = "hj",
            likes = 0,
            accidents = _accidentList.value,
        )
    }

}