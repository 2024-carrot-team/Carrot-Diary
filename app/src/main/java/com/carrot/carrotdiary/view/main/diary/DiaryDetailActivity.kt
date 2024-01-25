package com.carrot.carrotdiary.view.main.diary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carrot.carrotdiary.databinding.ActivityDiaryDetailBinding
import com.carrot.carrotdiary.model.Diary

class DiaryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = mutableListOf<Diary>()

        list.add(Diary(1, "title1", "guen", null, null, null))
        list.add(Diary(2, "title2", "guen", null, null, null))
        list.add(Diary(3, "title3", "guen", null, null, null))

        val adapter = DiaryDetailAdapter(list, this@DiaryDetailActivity)

        binding.containerRvDiaryDetail.adapter = adapter
        binding.containerRvDiaryDetail.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }
}