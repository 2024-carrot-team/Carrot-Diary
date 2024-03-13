package com.carrot.presentation.view.main.dailylist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.carrot.presentation.databinding.ActivityDailyListBinding
import com.carrot.presentation.view.main.writedaily.WriteDailyActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyHeaderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDailyListBinding

    private var postId: Int = 0

    private val viewModel: DailyListViewModel by viewModels()

    private lateinit var adapter: DailyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postId = intent.getIntExtra("diary", 0)

        adapter = DailyListAdapter(this)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (postId != 0) {
            viewModel.loadDailyHeaderList(postId)
        }
        initListener(this)
        binding.buttonMakeNewDaily.setOnClickListener {
            viewModel.makeDaily(postId)
        }
    }

    private fun initListener(context: Context) {
        lifecycleScope.launch {
            viewModel.dailyHeaderList.collect { dailyHeader ->
                adapter.updateDataSet(dailyHeader)
            }
        }
        lifecycleScope.launch {
            viewModel.newDailyId.collect { dailyId ->
                if (dailyId != 0) {
                    val intent = Intent(context, WriteDailyActivity::class.java)
                    intent.putExtra("diary", postId)
                    intent.putExtra("daily", dailyId)
                    startActivity(intent)
                }
            }
        }
    }
}