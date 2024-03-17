package com.carrot.presentation.view.main.writedaily

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.common.Dummy
import com.carrot.presentation.model.Accident
import com.carrot.presentation.model.Diary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WriteDailyViewModel @Inject constructor(
    private val sharedPreferencesService: SharedPreferencesService,
    private val api: LoginApiService
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

    private val _diaryId = MutableStateFlow<Int>(0)
    val diaryId = _diaryId.asStateFlow()

    private val _dailyId = MutableStateFlow<Int>(0)
    val dailyId = _dailyId.asStateFlow()

    //    val bitMapList = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitMapList = MutableStateFlow<List<MultipartBody.Part>>(emptyList())

    fun loadDiaryId(id: Int) {
//        _diary.value = Dummy.diaryList[id]
    }

    fun setDailyId(dailyId: Int) {
        _dailyId.value = dailyId
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
        viewModelScope.launch {
            api.postDaily(
                content = content.toRequestBody("text/plain".toMediaTypeOrNull()),
                authorization = sharedPreferencesService.cookie ?: "",
                images = bitMapList.value,
                postDiaryId = _dailyId.value.toString()
            )
        }
        _accidentList.value = _accidentList.value + Accident(
            imageList = _imageList.value,
            id = _accidentId.value,
            date = _date.value,
            content = content
        )
        _accidentId.value += 1
    }

    fun saveDaily() {

    }

    fun makeMultiPartBodyList(bitmap: Bitmap) {
        val imageRequestBody = BitmapRequestBody(bitmap)
        bitMapList.value = bitMapList.value + MultipartBody.Part.createFormData(
            "images",
            "and" + System.currentTimeMillis(),
            imageRequestBody
        )
//        val imageMultipartBody: MultipartBody.Part =
//            MultipartBody.Part.createFormData(
//                "image",
//                "and" + System.currentTimeMillis(),
//                imageRequestBody
//            )
    }

    companion object {
        class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
            override fun contentType(): MediaType? {
                return "image/JPEG".toMediaTypeOrNull()
            }

            override fun writeTo(sink: BufferedSink) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, sink.outputStream()) //99프로 압축
            }
        }
    }
}