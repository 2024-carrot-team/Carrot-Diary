package com.carrot.presentation.view.main.daily

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.carrot.presentation.databinding.FragmentShowDailyBinding
import com.carrot.presentation.model.MainDiaries
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.view.main.diary.DiaryDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowDailyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private val viewModel: DailyViewModel by viewModels()
    private var intent: Intent? = null
    lateinit var binding: FragmentShowDailyBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowDailyBinding.inflate(inflater, container, false)


        arguments?.takeIf { it.containsKey(DailyAdapter.DAILY_TYPE) }?.apply {
            var dailryType = getInt(DailyAdapter.DAILY_TYPE)
            when (dailryType) {
                0 -> { // 추천 일기 데이터 로드
                    viewModel.loadMainDaily()
                }

                1 -> { // 팔로우 일기 데이터 로드
                    //viewModel
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }


    private fun init() {
        val adapter = ShowDailyAdapter(
            onItemClickListener = {
                // 다이얼로그 디테일로 이동
                intent = Intent(context, DiaryDetailActivity::class.java)
                Log.d("guen", it.toString())
                intent?.putExtra("POST_DIARY_ID", it);
                startActivity(intent)
            }
        ).also { binding.recyclerShowShowDaily.adapter = it }


        lifecycleScope.launch {
            viewModel.mainDiary.collect { data ->
                data?.let {
                    adapter.submitList(data.dataList) // 최신 일기 넘겨주기
                }
            }
        }
    }
}