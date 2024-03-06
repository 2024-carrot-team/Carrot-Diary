package com.carrot.presentation.view.main.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.carrot.presentation.databinding.FragmentDiaryBinding
import com.google.android.material.tabs.TabLayoutMediator

class DailyFragment : Fragment() {

    private val fragmentTitleList = mutableListOf("추천", "팔로워")

    private lateinit var binding: FragmentDiaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDiaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerDiary.adapter = DailyAdapter(this)

        TabLayoutMediator(binding.tabLayoutDiary, binding.viewPagerDiary) { tab, position ->
            when (position) {
                0 -> tab.text = fragmentTitleList[0]
                1 -> tab.text = fragmentTitleList[1]
                else -> tab.text = "OBJECT ${(position + 1)}"
            }
        }.attach()
    }
}