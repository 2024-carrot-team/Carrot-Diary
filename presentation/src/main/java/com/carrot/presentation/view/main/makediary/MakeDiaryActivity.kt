package com.carrot.presentation.view.main.makediary

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
            if (uri != null) {
                println("체크 $uri")
                viewModel.makeDiary(
                    binding.editTextDiaryTitle.text.toString(),
                    uriToFile(uri)
//                    File(absolutelyPath(uri, this))
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

    private val activityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val intent = checkNotNull(result.data)
                val imageUri = intent.data
                val file = File(absolutelyPath(imageUri, this))
//                val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//                val body = MultipartBody.Part.createFormData("proFile", file.name, requestFile)
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

    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    private fun uriToFile(uri: Uri): File {
        val context = applicationContext
        val contentResolver = context.contentResolver
        var inputStream = contentResolver.openInputStream(uri)
        val outputFile = File(context.cacheDir, "temp_file")
        inputStream?.use { input ->
            FileOutputStream(outputFile).use { output ->
                input.copyTo(output)
            }
        }
//        inputStream?.let {
//            try {
//                val outputStream = FileOutputStream(outputFile)
//                val buf = ByteArray(1024)
//                var len: Int
//                while (inputStream.read(buf).also { len = it } > 0) {
//                    outputStream.write(buf, 0, len)
//                }
//                outputStream.close()
//                inputStream.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
        return outputFile
    }
}