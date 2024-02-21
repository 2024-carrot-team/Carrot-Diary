package com.carrot.carrotdiary.view.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.carrot.carrotdiary.databinding.FragmentProfileBinding
import com.carrot.carrotdiary.model.Diary
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private val fragmentTitleList = mutableListOf("나의 일기장", "팔로워")

    private var _binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels()
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = mutableListOf<Diary>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerDiary.adapter = ProfileAdapter(this, viewModel)

        TabLayoutMediator(
            binding.tabLayoutDiaryProfileFragment,
            binding.viewPagerDiary
        ) { tab, position ->
            when (position) {
                0 -> tab.text = fragmentTitleList[0]
                1 -> tab.text = fragmentTitleList[1]
                else -> tab.text = "OBJECT ${(position + 1)}"
            }
        }.attach()
    }
}