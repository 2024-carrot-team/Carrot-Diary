package com.carrot.presentation.view.main.makediary

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.carrot.presentation.databinding.ActivityMakeDiaryBinding
import com.carrot.presentation.view.main.writedaily.WriteDailyActivity

class MakeDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakeDiaryBinding

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
            val intent = Intent(this, WriteDailyActivity::class.java)
            startActivity(intent)
        }
    }

    private val activityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val intent = checkNotNull(result.data)
                val imageUri = intent.data
                Glide.with(this).load(imageUri).into(binding.imageIvMakeNewDiary)
            }
        }


}