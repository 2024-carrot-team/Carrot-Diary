package com.carrot.presentation.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class DailyAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        val fragment = ShowDailyFragment()
        fragment.arguments = Bundle().apply {
            putInt(DAILY_TYPE, position) // 0: 추천 일기, 1: 팔로워
        }
        return fragment
    }

    companion object {
        const val DAILY_TYPE = "dailyType"
    }
}