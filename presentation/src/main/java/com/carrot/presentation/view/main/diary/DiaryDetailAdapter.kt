package com.carrot.presentation.view.main.diary

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carrot.data.model.DiaryDetailDTO
import com.carrot.data.model.ImageInfo
import com.carrot.presentation.databinding.ItemContentBinding
import com.carrot.presentation.databinding.ItemDiaryDetailBinding
import com.carrot.presentation.databinding.ItemImageBinding

class DiaryDetailAdapter(
    private val context: Context,
    private val viewModel: DiaryDetailViewModel,
) :
    RecyclerView.Adapter<DiaryDetailAdapter.DiaryViewHolder>() {
    private lateinit var diaryList: List<String>
    fun setData(diaryList: List<String>) {
        this.diaryList = diaryList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryDetailAdapter.DiaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DiaryDetailAdapter.DiaryViewHolder(
            ItemDiaryDetailBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = diaryList[position]
        if (!item.isNullOrBlank() && !item.isNullOrEmpty()) {
            holder.bind(context, item, viewModel)
        }
    }

    class DiaryViewHolder(private val binding: ItemDiaryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, item: String, viewModel: DiaryDetailViewModel) {

            // 일기 데이터
            val diaryDetailDTO: DiaryDetailDTO? = viewModel.loadDetailDaily(item)

            binding.contentTvDiaryDetail.text = diaryDetailDTO?.data!!.content

            val imageAdapter = ImageAdapter(context, diaryDetailDTO?.data!!.imageInfo)
            binding.imageRvDiaryDetail.adapter = imageAdapter
            binding.imageRvDiaryDetail.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}

class ContentAdapter(private val contentList: ArrayList<String>) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContentAdapter.ContentViewHolder(
            ItemContentBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    fun setData() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val item = contentList[position]

        holder.bind(item)

    }


    class ContentViewHolder(private val itemContentBinding: ItemContentBinding) :
        RecyclerView.ViewHolder(itemContentBinding.root) {

        fun bind(item: String) {
            itemContentBinding.contentTvDiary.text = "재미난 하루 ${item}"
        }
    }
}


class ImageAdapter(private val context: Context, private val imageList: List<ImageInfo>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageAdapter.ImageViewHolder(
            ItemImageBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    fun setData() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList[position]

        holder.bind(context, item)

    }


    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, item: ImageInfo) {
            Glide.with(context).load(item.imageUrl).into(binding.imageIvDiary)
        }
    }
}




