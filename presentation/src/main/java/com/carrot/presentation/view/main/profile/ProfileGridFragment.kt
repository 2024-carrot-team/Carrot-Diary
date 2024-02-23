package com.carrot.presentation.view.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.databinding.FragmentProfileGridBinding


class ProfileGridFragment(
    private val viewModel: ProfileViewModel
) : Fragment() {
    private var _binding: FragmentProfileGridBinding? = null

//    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var recyclerViewGrid: RecyclerView
    private val binding
        get() = _binding!!

    private val diaryListAdapter = DiaryListAdapter(viewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileGridBinding.inflate(inflater, container, false)

        recyclerViewGrid = binding.recyclerviewProfileGridProfileGridFragment
        recyclerViewGrid.adapter = diaryListAdapter
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        recyclerViewGrid.apply {
            layoutManager = GridLayoutManager(this@ProfileGridFragment.context, 3)
            adapter = diaryListAdapter
        }
    }
}