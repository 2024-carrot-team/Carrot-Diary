package com.carrot.presentation.view.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.databinding.FragmentProfileGridBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileGridFragment(

) : Fragment() {
    private var _binding: FragmentProfileGridBinding? = null

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var recyclerViewGrid: RecyclerView
    private val binding
        get() = _binding!!

    private val diaryListAdapter = DiaryListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileGridBinding.inflate(inflater, container, false)

        recyclerViewGrid = binding.recyclerviewProfileGridProfileGridFragment
        recyclerViewGrid.adapter = diaryListAdapter
        initRecyclerView()
        viewModel.loadDiaryTitleList()
        return binding.root
    }

    private fun initRecyclerView() {
        recyclerViewGrid.apply {
            layoutManager = GridLayoutManager(this@ProfileGridFragment.context, 3)
            adapter = diaryListAdapter
        }
        lifecycleScope.launch {
            viewModel.diaryList.collect { diaryList ->
                diaryListAdapter.submitList(diaryList)
            }
        }

    }
}