package com.carrot.presentation.view.main.daily

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ListAdapter
import com.carrot.presentation.R
import com.carrot.presentation.common.ReportType
import com.carrot.presentation.databinding.DialogReportFrameBinding
import com.carrot.presentation.databinding.FragmentShowDailyBinding
import com.carrot.presentation.model.MainDiaries
import com.carrot.presentation.model.MainDiary
import com.carrot.presentation.view.dialog.DialogBuilder
import com.carrot.presentation.view.main.MainActivity
import com.carrot.presentation.view.main.diary.DiaryDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowDailyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private val viewModel: DailyViewModel by viewModels()
    private var intent: Intent? = null
    lateinit var binding: FragmentShowDailyBinding
    lateinit var dialogBinding: DialogReportFrameBinding
    lateinit var reportType: ReportType
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowDailyBinding.inflate(inflater, container, false)


        arguments?.takeIf { it.containsKey(DailyAdapter.DAILY_TYPE) }?.apply {
            var dailryType = getInt(DailyAdapter.DAILY_TYPE)
            when (dailryType) {
                0 -> { // 추천 일기 데이터 로드
                    viewModel.loadMainDaily()
                }

                1 -> { // 팔로우 일기 데이터 로드
                    //viewModel
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }


    private fun init() {
        val adapter = ShowDailyAdapter(
            onItemClickListener = {
                println("ShowDailyAdapter 작동")
                // 다이얼로그 디테일로 이동
                intent = Intent(context, DiaryDetailActivity::class.java)
                Log.d("guen", it.toString())
                intent?.putExtra("POST_DIARY_ID", it);
                startActivity(intent)
            },
            onReportClickListener = {
                val reportList = listOf("폭력적인 내용", "수치적인 내용", "선정적인 내용", "기타")
                var builder = AlertDialog.Builder(context)
                dialogBinding =
                    DialogReportFrameBinding.inflate(LayoutInflater.from(this.requireContext()))
                builder.setView(dialogBinding.root)
                val spinner = dialogBinding.processSpinner
                val spinneradapter =
                    ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        reportList
                    )
                spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = spinneradapter
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when (position) {
                            0 -> {
                                reportType = ReportType.VIOLENT_CONTENT
                            }

                            1 -> {
                                reportType = ReportType.OFFENSIVE_CONTENT
                            }

                            2 -> {
                                reportType = ReportType.SEXUAL_CONTENT
                            }

                            3 -> {
                                reportType = ReportType.OTHER
                            }
                        }



                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        reportType = ReportType.VIOLENT_CONTENT
                    }
                }
                val report = DialogInterface.OnClickListener { dialog, which ->
                    viewModel.report(
                        reportType = reportType.toString(),
                        content = dialogBinding.editTextReportContent.text.toString()
                    )
                }
                builder.setPositiveButton("확인", report)
                builder.setNegativeButton("취소", null)
                builder.show()
            }
        ).also { binding.recyclerShowShowDaily.adapter = it }

        setUpSwipeRefresh(adapter)

        lifecycleScope.launch {
            viewModel.mainDiary.collect { data ->
                data?.let {
                    adapter.submitList(data.dataList) // 최신 일기 넘겨주기
                }
            }
        }
    }


    private fun setUpSwipeRefresh(adapter: ShowDailyAdapter) {
        binding.swipeLayoutRefreshShowDaily.setOnRefreshListener {
            viewModel.loadMainDaily()
            binding.swipeLayoutRefreshShowDaily.isRefreshing = false
        }
    }


}