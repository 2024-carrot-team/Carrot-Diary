package com.carrot.presentation.view.main.daily

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.carrot.presentation.databinding.FragmentDiaryBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment : Fragment() {

    private val fragmentTitleList = mutableListOf("추천", "팔로워")

    private var _binding: FragmentDiaryBinding? = null

    private val viewModel: DailyViewModel by viewModels()
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
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