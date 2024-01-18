package com.carrot.carrotdiary.view.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.carrot.carrotdiary.databinding.FragmentShowDailyBinding
import com.carrot.carrotdiary.model.Daily


class ShowDailyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
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

        val binding = FragmentShowDailyBinding.inflate(inflater, container, false)


        val list = mutableListOf<Daily>()

        for (i in 0 until 10) {
            val item = Daily(i, null, i, null, null)
            list.add(i, item)
        }
        var adapter = ShowDailyAdapter(mainActivity.baseContext)

        binding.recyclerShowShowDaily.adapter = adapter

        arguments?.takeIf { it.containsKey(DailyAdapter.DAILY_TYPE) }?.apply {
            var dailryType = getInt(DailyAdapter.DAILY_TYPE)
            when (dailryType) {
                0 -> { // 추천 일기 데이터
                    adapter.submitList(list)

                }

                1 -> { // 팔로우 일기 데이터
                    adapter.submitList(list)
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}