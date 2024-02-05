package com.carrot.carrotdiary.view.main.dailylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.carrot.carrotdiary.databinding.FragmentDailyListBinding

class DailyListFragment : Fragment() {
    private lateinit var binding: FragmentDailyListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyListBinding.inflate(inflater, container, false)




        return binding.root
    }

}