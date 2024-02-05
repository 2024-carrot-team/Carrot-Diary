package com.carrot.carrotdiary.view.makediary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.carrot.carrotdiary.databinding.FragmentMakeNewDiaryBinding


class MakeNewDiaryFragment : Fragment() {

    private lateinit var binding: FragmentMakeNewDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeNewDiaryBinding.inflate(inflater, container, false)

        return binding.root
    }

}