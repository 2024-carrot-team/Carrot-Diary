package com.carrot.presentation.view.main.profile

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carrot.presentation.R
import com.carrot.presentation.databinding.ItemThumbnailDiaryBinding


class DiaryListAdapter(
    private val viewModel: ProfileViewModel
) :
    RecyclerView.Adapter<DiaryListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thumbnail_diary, parent, false)
        return ViewHolder(ItemThumbnailDiaryBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.diaryTitle.text = viewModel.diaryList.value[position].title
        Glide.with(holder.itemView).load(Uri.parse(viewModel.diaryList.value[position].cover))
            .into(holder.thumbnailImage)
//        holder.thumbnailImage.setImageURI(Uri.parse(items[position].cover))
//        holder.thumbnailImage
    }

    override fun getItemCount(): Int {
        return viewModel.diaryList.value.size
    }

    class ViewHolder(binding: ItemThumbnailDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        val thumbnailImage = binding.imageViewThumbnailItemThumbnailDiary
        val diaryTitle = binding.textViewContentItemThumbnailDiary
    }
}
