package com.carrot.presentation.view.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.carrot.presentation.databinding.FragmentProfileBinding
import com.carrot.presentation.model.Diary
import com.carrot.presentation.view.SettingActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private val fragmentTitleList = mutableListOf("나의 일기장", "팔로워")

    private var _binding: FragmentProfileBinding? = null
//    private val viewModel: ProfileViewModel by viewModels()

    private val viewModel: ProfileViewModel by activityViewModels()
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

        binding.buttonSettingProfileFragment.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }

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