package com.carrot.presentation.view.main.makediary

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.model.TitleRequest
import com.carrot.data.model.WriteDiary
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.common.Dummy
import com.carrot.presentation.model.Diary
import com.carrot.presentation.util.MultipartBodyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MakeDiaryViewModel @Inject constructor(
    private val sharedPreferencesService: SharedPreferencesService,
    private val loginApiService: LoginApiService
) : ViewModel() {

    val _diaryList = MutableLiveData<List<Diary>>()
    val diaryList: LiveData<List<Diary>> get() = _diaryList
    private val _selectedUri = MutableStateFlow<Uri?>(null)
    val selectedUri = _selectedUri.asStateFlow()

    val selectedFile = MutableStateFlow<File?>(null)

    init {
        dummyDiary()
    }

    fun dummyDiary() {
        _diaryList.value = Dummy.diaryList
    }

    fun test(title: String) {

    }

    fun makeDiary(title: String, bitmap: Bitmap) {
        val imageRequestBody = BitmapRequestBody(bitmap)
        val imageMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "image",
                "and" + System.currentTimeMillis(),
                imageRequestBody
            )
//        val file = File(filePath)
        val diaryTitle = title.toRequestBody("text/plain".toMediaTypeOrNull())
//        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//
//        // 실제 요청 생성
//        val requestFile = MultipartBody.Part.createFormData("image", file.name, requestBody)
//        val requestTitle = MultipartBody.Part.createFormData("title", "", diaryTitle)
//        println("파일 ${file.exists()} $filePath")
        viewModelScope.launch {
            try {
                val result = loginApiService.postDiary(
                    authorization = sharedPreferencesService.cookie ?: "",
                    diaryTitle,
                    imageMultipartBody
                )
                println("결과1 $result")
            } catch (e: Exception) {
                println("에러 $e")
            }
        }
    }

    companion object {
        class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
            override fun contentType(): MediaType? {
                return "image/png".toMediaTypeOrNull()
            }

            override fun writeTo(sink: BufferedSink) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 99, sink.outputStream()) //99프로 압축
            }
        }
    }
//        viewModelScope.launch {
//            try {
//                val result = loginApiService.postDiary(
//                    title, requestFile
//                )
//                println("결과2 $result")
//            } catch (e: Exception) {
//                println("에러 $e")
//            }
//        }
//    }


    fun setUri(uri: Uri) {
        _selectedUri.value = uri
    }


//    fun setFile(file: File) {
//        selectedFile.value = file
//    }

}