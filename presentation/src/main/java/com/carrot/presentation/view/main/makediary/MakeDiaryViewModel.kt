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
import com.carrot.presentation.model.DiaryHeader
import com.carrot.presentation.model.mapper.toView
import com.carrot.presentation.util.MultipartBodyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
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

    private val _diaryList = MutableLiveData<List<DiaryHeader>>()
    val diaryList: LiveData<List<DiaryHeader>> get() = _diaryList
    private val _selectedUri = MutableStateFlow<Uri?>(null)
    val selectedUri = _selectedUri.asStateFlow()

    val selectedFile = MutableStateFlow<File?>(null)

    val _responseCode = MutableStateFlow<Int>(0)

    val responseCode = _responseCode.asStateFlow()

    init {
        loadPostId()
    }

    fun loadPostId() {
        viewModelScope.launch {
            val result = loginApiService.getDiary(
                authorization = sharedPreferencesService.cookie ?: ""
            )
            if (result.isSuccessful && result.code() == 200) {
                result.body().let { diaryListDTO ->
                    _diaryList.value = diaryListDTO!!.toView()
                }
            }
        }
    }

    fun makeDiary(title: String, bitmap: Bitmap) {

        viewModelScope.launch {
            val imageRequestBody = BitmapRequestBody(bitmap)
            val imageMultipartBody: MultipartBody.Part =
                MultipartBody.Part.createFormData(
                    "image",
                    "and" + System.currentTimeMillis(),
                    imageRequestBody
                )
            val diaryTitle = title.toRequestBody("text/plain".toMediaTypeOrNull())
            try {
                val result = loginApiService.postDiary(
                    authorization = sharedPreferencesService.cookie ?: "",
                    diaryTitle,
                    imageMultipartBody
                )
                println("결과 코드 ${result.code()}")
                _responseCode.value = result.code()
            } catch (e: Exception) {
                println("에러 $e")
            }
        }
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

    fun setUri(uri: Uri) {
        _selectedUri.value = uri
    }


}