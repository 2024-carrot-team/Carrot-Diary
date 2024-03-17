package com.carrot.presentation.view.main.diary

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.carrot.presentation.databinding.ActivityDiaryDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiaryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryDetailBinding
    private val viewModel: DiaryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val postDiaryId = getDiaryId()

        // 일기장의 일기들 load
        viewModel.getPostDiary(postDiaryId.toString()) //api 호출

        val adapter = DiaryDetailAdapter(this@DiaryDetailActivity, viewModel)   // 일기와 연결할 어뎁터 -> 이 어뎁터 안에서 일기의 사건들 호출 및 바인딩
        binding.containerRvDiaryDetail.adapter = adapter
        lifecycleScope.launch {
            viewModel.seq.collect {
                val seqList = it.split("-").toList()
                adapter.setData(seqList)
            }
        }
        binding.containerRvDiaryDetail.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        adapter.notifyDataSetChanged()
    }

    private fun getDiaryId(): Long {
        val postDiaryId = intent.getLongExtra("POST_DIARY_ID", 0)
        Log.d("guen get", postDiaryId.toString())
        return postDiaryId
    }
}