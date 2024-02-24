package com.carrot.presentation.view.main.makediary

import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carrot.data.local.SharedPreferencesService
import com.carrot.data.model.WriteDiary
import com.carrot.data.remote.api.LoginApiService
import com.carrot.presentation.common.Dummy
import com.carrot.presentation.model.Diary
import com.carrot.presentation.util.MultipartBodyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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

    fun makeDiary(title: String, file: File) {
//        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val diaryTitle = MultipartBodyUtil.getBody("title", title)
        val imageFile = MultipartBodyUtil.getImageBody("image", file)
        println("파일 $file")
//        val titleRequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
//        val imageRequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//        val imagePart = MultipartBody.Part.createFormData("image", file.name, imageRequestBody)
        viewModelScope.launch {
            try {
                val result = loginApiService.postDiary(
                    diaryTitle, imageFile
                )
                println(result)
            } catch (e: Exception) {
                println(e)
            }
        }
    }


    fun setUri(uri: Uri) {
        _selectedUri.value = uri
    }


//    fun setFile(file: File) {
//        selectedFile.value = file
//    }

}