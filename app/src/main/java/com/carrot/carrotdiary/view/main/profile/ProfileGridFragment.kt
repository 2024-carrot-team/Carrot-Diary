package com.carrot.carrotdiary.view.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carrot.carrotdiary.databinding.FragmentProfileGridBinding


class ProfileGridFragment : Fragment() {
    private var _binding: FragmentProfileGridBinding? = null

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var recyclerViewGrid: RecyclerView
    private val binding
        get() = _binding!!

    private val diaryListAdapter = DiaryListAdapter()

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
            layoutManager = GridLayoutManager(this@ProfileGridFragment.context, 2)
            adapter = diaryListAdapter
        }
    }
}