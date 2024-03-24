package com.carrot.presentation.view.main.dailylist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
    private var diaryTitle: String = ""

    private val viewModel: DailyListViewModel by viewModels()

    private lateinit var adapter: DailyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postId = intent.getIntExtra("diary", 0)

        println("DailyHeaderListActivity postId값 $postId")
        diaryTitle = intent.getStringExtra("diary_title") ?: ""

        adapter = DailyListAdapter(this,
            onItemClickListener = { dailyHeader ->
                val intent = Intent(this, WriteDailyActivity::class.java)
                intent.putExtra("diary", postId)
                intent.putExtra("daily", dailyHeader.postDiaryId)
                intent.putExtra("daily_date", dailyHeader.yearMonthDay)
                intent.putExtra("diary_title", diaryTitle)
                startActivity(intent)
            },
            onDeleteClickListener = { postDiaryId, position ->
                viewModel.deleteDaily(
                    postDiaryId,
                )
                adapter.deleteItem(position)
            },
        )
        setActionBar()
        loadItemData()
        setRecyclerView()
        initListener(this)

    }

    private fun setRecyclerView() {
        binding.recyclerViewDailyListActivity.adapter = adapter
        binding.recyclerViewDailyListActivity.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setActionBar () {
        supportActionBar?.title = diaryTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
                    intent.putExtra("diary_title", diaryTitle)
                    startActivity(intent)
                }
            }
        }
        binding.buttonMakeNewDailyDailyListActivity.setOnClickListener {
            viewModel.makeDaily(postId)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> { // 뒤로가기버튼
                finish()
                return true;
            }

            else -> {
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onRestart() {
        super.onRestart()
        adapter.clearAllData()
        loadItemData()
    }

    private fun loadItemData() {
        if (postId != 0) {
            viewModel.loadDailyHeaderList(postId)
        }
    }

}