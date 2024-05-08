package com.carrot.presentation.view.main.profile

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carrot.presentation.R
import com.carrot.presentation.databinding.ItemThumbnailDiaryBinding
import com.carrot.presentation.model.Diary
import com.carrot.presentation.model.DiaryHeader


class DiaryListAdapter() : ListAdapter<DiaryHeader, DiaryListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<DiaryHeader>() {
        override fun areItemsTheSame(
            oldItem: DiaryHeader,
            newItem: DiaryHeader
        ): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(
            oldItem: DiaryHeader,
            newItem: DiaryHeader
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListAdapter.ViewHolder {
        val binding =
            ItemThumbnailDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            item = getItem(position)
        )
    }

    class ViewHolder(
        private val binding: ItemThumbnailDiaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DiaryHeader) {
            Glide.with(binding.root).load(Uri.parse(item.imageUrl))
                .into(binding.imageViewThumbnailItemThumbnailDiary)
            binding.textViewContentItemThumbnailDiary.text = item.title
        }
    }


}
