package com.carrot.presentation.view.main.makediary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.carrot.presentation.databinding.FragmentDiaryWriteDialogBinding
import com.carrot.presentation.model.Diary
import com.carrot.presentation.view.main.writedaily.WriteDailyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryWriteDialogFragment : DialogFragment() {
    private var _binding: FragmentDiaryWriteDialogBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: MakeDiaryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiaryWriteDialogBinding.inflate(inflater, container, false)
        binding.buttonMakeNewDiaryDiaryWriteDialog.setOnClickListener {
            val intent = Intent(context, MakeDiaryActivity::class.java)
            startActivity(intent)
        }
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.diaryList.observe(this) {
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewDiaryListDiaryWriteDialog.adapter =
            DiaryTitleListAdapter(viewModel, onItemClickListener = { diary: Diary ->
                val intent = Intent(context, WriteDailyActivity::class.java)
                intent.putExtra("diary", diary.id)
                startActivity(intent)
            })
    }
}