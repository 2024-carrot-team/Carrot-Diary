package com.carrot.presentation.view.main.writedaily

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.lifecycleScope
import com.carrot.presentation.databinding.ActivityWriteDailyBinding
import com.carrot.presentation.databinding.DialogAccidentMakeBinding
import com.carrot.presentation.view.main.dailylist.DailyHeaderListActivity
import com.carrot.presentation.view.main.makediary.AccidentImageListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class WriteDailyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteDailyBinding
    private lateinit var dialogBinding: DialogAccidentMakeBinding
    private val dateTimeFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
    private val viewModel: WriteDailyViewModel by viewModels()
    private val imageListAdapter = AccidentImageListAdapter()
    private val accidentListAdapter = AccidentListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setCookie()
//        viewModel.loadDiaryId(intent.getIntExtra("diary", 0))
        binding.buttonMakeNewAccidentWriteDailyActivity.setOnClickListener { writeAccident() }
        binding.recyclerViewAccidentListWriteDailyActivity.adapter = accidentListAdapter
        val postId = intent.getIntExtra("diary", 0)
        val dailyId = intent.getIntExtra("daily", 0)
        viewModel.setDailyId(dailyId)
        binding.buttonRegiDailyWriteDailyActivity.setOnClickListener {
            val intent = Intent(this, DailyHeaderListActivity::class.java)
            intent.putExtra("diary", postId)
            startActivity(intent)
        }
        binding.buttonRegiDailyWriteDailyActivity.setOnClickListener {
            viewModel.saveDaily()
        }
        initLiveData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initLiveData() {
        lifecycleScope.launch {
            viewModel.diary.collect { diary ->
                binding.textViewDailyTitleWriteDailyActivity.text = diary.title
            }
        }
        lifecycleScope.launch {
            viewModel.date.collect { date ->
                binding.textViewDailyDateWriteDailyActivity.text = date
            }
        }
        lifecycleScope.launch {
            viewModel.imageList.collect { imageList ->
                imageListAdapter.addItems(imageList)
                imageListAdapter.notifyDataSetChanged()
            }
        }
        lifecycleScope.launch {
            viewModel.accidentList.collect { accidents ->
                accidentListAdapter.addAccident(accidents)
                accidentListAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun writeAccident() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("기억나는 일 적기")
        dialogBinding = DialogAccidentMakeBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        dialogBinding.textViewBlankImgAccidentMakeDialog.setOnClickListener {
            if (viewModel.imageList.value.size > 5) {
                Toast.makeText(this, "이미지는 최대 5장까지 첨부할 수 있습니다.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            registerImageActivityResult.launch(intent)
        }
        dialogBinding.recyclerViewAccidentImageListAccidentMakeDialog.adapter = imageListAdapter
        val showDialog = builder.create()
        showDialog.show()
        dialogBinding.buttonMakeNewAccidentAccidentMakeDialog.setOnClickListener {
            viewModel.addAccident(dialogBinding.editTextAccidentContextAccidentMakeDialog.text.toString())
            viewModel.clearImage()
            showDialog.dismiss()
        }
    }

    private val registerImageActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                AppCompatActivity.RESULT_OK -> {
                    val clipData = result.data?.clipData
                    if (clipData != null) {
                        if (clipData.itemCount > 5) {
                            Toast.makeText(this, "이미지는 최대 5장까지 첨부할 수 있습니다.", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            viewModel.clearImage()
                            for (i in 0 until clipData.itemCount) {
                                viewModel.addImage(
                                    clipData.getItemAt(i).uri.toString()
                                )
                                viewModel.makeMultiPartBodyList(
                                    getBitmapFromUri(
                                        clipData.getItemAt(
                                            i
                                        ).uri
                                    )
                                )
                            }
                            dialogBinding.textViewBlankImgAccidentMakeDialog.isInvisible = true
                        }
                    }
                }
            }
        }

    private fun getBitmapFromUri(uri: Uri) = if (Build.VERSION.SDK_INT >= 28) {
        val source = ImageDecoder.createSource(application.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    } else {
        val bitmap = MediaStore.Images.Media.getBitmap(application.contentResolver, uri)
        Bitmap.createScaledBitmap(bitmap, bitmap.width / 2, bitmap.height / 2, true)
    }

}