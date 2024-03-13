package com.carrot.presentation.view.main.makediary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.model.Diary
import com.carrot.presentation.R
import com.carrot.presentation.databinding.ItemDiaryTitleBinding
import com.carrot.presentation.model.DiaryHeader

class DiaryTitleListAdapter(
    private val viewModel: MakeDiaryViewModel,
    val onItemClickListener: ((DiaryHeader) -> Unit)
) : RecyclerView.Adapter<DiaryTitleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DiaryTitleListAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_diary_title, parent, false)
        return ViewHolder(ItemDiaryTitleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            viewModel.diaryList.value!![position],
            onItemClickListener = onItemClickListener
        )
    }

    override fun getItemCount(): Int {
        return viewModel.diaryList.value?.size ?: 0
    }

    class ViewHolder(val binding: ItemDiaryTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diary: DiaryHeader, onItemClickListener: ((DiaryHeader) -> Unit)) {
            binding.textViewTitleDiaryTitleItem.text = diary.title
            binding.root.setOnClickListener {
                onItemClickListener.invoke(diary)
            }
        }
    }
}