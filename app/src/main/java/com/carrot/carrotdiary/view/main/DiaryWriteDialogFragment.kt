package com.carrot.carrotdiary.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.carrot.carrotdiary.databinding.FragmentDiaryWriteDialogBinding

class DiaryWriteDialogFragment : DialogFragment() {

    private lateinit var diaryWriteDialogBinding: FragmentDiaryWriteDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryWriteDialogBinding.inflate(inflater, container, false)
        diaryWriteDialogBinding = binding
        return binding.root
    }
}