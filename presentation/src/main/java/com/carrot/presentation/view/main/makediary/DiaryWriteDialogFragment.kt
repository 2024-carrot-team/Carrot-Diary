package com.carrot.presentation.view.main.makediary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.carrot.presentation.databinding.FragmentDiaryWriteDialogBinding
import com.carrot.presentation.model.DiaryHeader
import com.carrot.presentation.view.main.dailylist.DailyHeaderListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiaryWriteDialogFragment : DialogFragment() {
    private var _binding: FragmentDiaryWriteDialogBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: MakeDiaryViewModel by viewModels()


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
        lifecycleScope.launch {
            viewModel.diaryListExist.collect { isExist ->
                binding.textViewNoDiaryListDiaryWriteDialog.isVisible = !isExist
            }
        }

    }

    private fun initRecyclerView() {
        binding.recyclerViewDiaryListDiaryWriteDialog.adapter =
            DiaryTitleListAdapter(viewModel, onItemClickListener = { diary: DiaryHeader ->
                val intent = Intent(context, DailyHeaderListActivity::class.java)
                println("DiaryWriteDialogFragment postId값 ${diary.postId}")
                intent.putExtra("diary", diary.postId)
                intent.putExtra("diary_title", diary.title)
                startActivity(intent)
            })
    }
}