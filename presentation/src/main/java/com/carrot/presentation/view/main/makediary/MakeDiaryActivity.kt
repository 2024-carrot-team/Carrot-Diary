package com.carrot.presentation.view.main.makediary


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.carrot.presentation.databinding.ActivityMakeDiaryBinding
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.view.main.writedaily.WriteDailyActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class MakeDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakeDiaryBinding

    private val viewModel: MakeDiaryViewModel by viewModels()

    private var requestImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.blankImageIvMakeNewDiary.setOnClickListener {
            val intent = Intent().also { intent ->
                intent.type = "image/"
                intent.action = Intent.ACTION_GET_CONTENT
            }
            activityResult.launch(intent)
        }
        binding.buttonMakeNewDiaryDiaryWriteDialog.setOnClickListener {
            val uri = viewModel.selectedUri.value
//            viewModel.test(binding.editTextDiaryTitle.text.toString())
            if (uri != null) {
                println("체크 $uri")
                viewModel.makeDiary(
                    binding.editTextDiaryTitle.text.toString(),
                    getBitmapFromUri(uri)
                )
            }
        }
        responseCodeListener()
    }

    private fun responseCodeListener() {
        lifecycleScope.launch {
            viewModel.responseCode.collect { resultCode ->
                if (resultCode == 201 || resultCode == 200) {
                    val intent = Intent(this@MakeDiaryActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri) = if (Build.VERSION.SDK_INT >= 28) {
        val source = ImageDecoder.createSource(application.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    } else {
//        MediaStore.Images.Media.getBitmap(application.contentResolver, uri)
        val bitmap = MediaStore.Images.Media.getBitmap(application.contentResolver, uri)
        Bitmap.createScaledBitmap(bitmap, bitmap.width / 2, bitmap.height / 2, true)
    }

    private fun resizedImage(uri: Uri) {
        if (Build.VERSION.SDK_INT >= 28) {
            val source = ImageDecoder.createSource(application.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            val bitmap = MediaStore.Images.Media.getBitmap(application.contentResolver, uri)
            val resizedBitmap =
                Bitmap.createScaledBitmap(bitmap, bitmap.width / 2, bitmap.height / 2, true)
        }
    }



    private val activityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val intent = checkNotNull(result.data)
                val imageUri = intent.data
                if (imageUri != null) {
                    viewModel.setUri(imageUri)
                    Glide.with(this).load(imageUri).into(binding.imageIvMakeNewDiary)
                }
            }
        }
}