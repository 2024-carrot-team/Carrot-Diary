package com.carrot.presentation.view.main.daily


import android.net.Uri
import android.opengl.Visibility
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carrot.presentation.databinding.ItemDailyBinding
import com.carrot.presentation.model.MainData
import com.carrot.presentation.model.MainDiary

// 나중에 페이징 어뎁터로 리펙토링
class ShowDailyAdapter(
    private val onItemClickListener: ((Long) -> Unit),
    private val onReportClickListener: ((Long) -> Unit),
) :
    ListAdapter<MainDiary, ShowDailyAdapter.DailyViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MainDiary>() {
            override fun areItemsTheSame(oldItem: MainDiary, newItem: MainDiary): Boolean {
                return oldItem.diaries[0].diaryId == newItem.diaries[0].diaryId
            }

            override fun areContentsTheSame(
                oldItem: MainDiary,
                newItem: MainDiary
            ): Boolean {
                return oldItem.diaries[0].diaryId == newItem.diaries[0].diaryId
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return DailyViewHolder(
            ItemDailyBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }
    fun DatasetClear() {
        submitList(null)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, position, onItemClickListener, onReportClickListener)
        }
    }

    class DailyViewHolder(private val binding: ItemDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: MainDiary,
            position: Int,
            onItemClickListener: ((Long) -> Unit),
            onReportClickListener: ((Long) -> Unit)
        ) {
            //신고 다이얼
            binding.moreIbDaily.setOnClickListener {
                onReportClickListener.invoke(item.postDiaryId)
            }
            binding.contentTvDaily.text = item.diaries[0].content
            Glide.with(binding.root).load(Uri.parse(item.diaries[0].imageInfo[0].imageUrl))
                .into(binding.ivDaily)
            binding.root.setOnClickListener { onItemClickListener.invoke(item.postDiaryId) }
        }
    }
}
