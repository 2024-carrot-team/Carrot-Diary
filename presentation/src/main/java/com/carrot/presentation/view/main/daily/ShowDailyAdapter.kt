package com.carrot.presentation.view.main.daily


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.databinding.ItemDailyBinding
import com.carrot.data.model.MainDiaries

// 나중에 페이징 어뎁터로 리펙토링
class ShowDailyAdapter(private val onItemClickListener:((MainDiaries) -> Unit)) :
    ListAdapter<MainDiaries, ShowDailyAdapter.DailyViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MainDiaries>() {
            override fun areItemsTheSame(oldItem: MainDiaries, newItem: MainDiaries): Boolean {
                return oldItem.diaries[0].diaryId == newItem.diaries[0].diaryId
            }

            override fun areContentsTheSame(
                oldItem: MainDiaries,
                newItem: MainDiaries
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

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onItemClickListener)
        }
    }

    class DailyViewHolder(private val itemDailyBinding: ItemDailyBinding) :
        RecyclerView.ViewHolder(itemDailyBinding.root) {
        fun bind(item: MainDiaries, onItemClickListener:((MainDiaries) -> Unit)) {

            itemDailyBinding.contentTvDaily.text = item.diaries[0].content
            // itemDailyBinding.ivDaily.src      글라이드 모듈 만들기

            itemDailyBinding.root.setOnClickListener{onItemClickListener.invoke(item)}
        }
    }
}
