package com.carrot.presentation.view.main.makediary

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.carrot.presentation.databinding.ActivityMakeDiaryBinding
import com.carrot.presentation.view.main.writedaily.WriteDailyActivity
import dagger.hilt.android.AndroidEntryPoint
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
//            val uri = viewModel.selectedUri.value
//            if (uri != null) {
//                viewModel.makeDiary(binding.editTextDiaryTitle.text.toString(), uriToFile(uri))
//            }
//            val intent = Intent(this, WriteDailyActivity::class.java)
//            startActivity(intent)
        }
    }

    private fun getBitmapFromUri(uri: Uri) = if (Build.VERSION.SDK_INT >= 28) {
        val source = ImageDecoder.createSource(application.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    } else {
        MediaStore.Images.Media.getBitmap(application.contentResolver, uri)
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

//                if (imageUri != null) {
//                    viewModel.setUri(imageUri)
//                    Glide.with(this).load(imageUri).into(binding.imageIvMakeNewDiary)
//                }
            }
        }


    private fun uriToFilePath(uri: Uri): String? {
        var filePath: String? = null
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = this.contentResolver.query(uri, projection, null, null, null)
            cursor?.use {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.moveToFirst()
                filePath = it.getString(columnIndex)
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            filePath = uri.path
        }
        return filePath
    }

    // 절대경로 변환
    fun absolutelyPath(path: Uri): String {

        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor = contentResolver.query(path, proj, null, null, null)!!
        var index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c.moveToFirst()

        var result = c.getString(index)

        return result
    }

    fun uriToFile(uri: Uri): File? {
        val contentResolver: ContentResolver = this.contentResolver
        val filePath = this.cacheDir // Or you can choose your own directory
        val file = File(filePath, "${System.currentTimeMillis()}") // Temporary file name

        try {
            val inputStream = contentResolver.openInputStream(uri)
            inputStream?.use { input ->
                val outputStream = FileOutputStream(file)
                outputStream.use { output ->
                    val buffer = ByteArray(4 * 1024) // buffer size
                    var read: Int
                    while (input.read(buffer).also { read = it } != -1) {
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                }
            }
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}