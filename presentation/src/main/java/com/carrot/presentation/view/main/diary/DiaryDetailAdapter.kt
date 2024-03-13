package com.carrot.presentation.view.main.diary

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carrot.presentation.R
import com.carrot.presentation.databinding.ItemContentBinding
import com.carrot.presentation.databinding.ItemDiaryDetailBinding
import com.carrot.presentation.databinding.ItemImageBinding
import com.carrot.presentation.model.Daily
import com.carrot.presentation.model.Diary

class DiaryDetailAdapter(private val diaryList: List<Diary>, private val context: Context) :
    RecyclerView.Adapter<DiaryDetailAdapter.DiaryViewHolder>() {

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
        holder.bind(item.dailyList, context)

        Log.d("test", item.id.toString())
        // }

    }

    class DiaryViewHolder(private val itemBinding: ItemDiaryDetailBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: List<Daily>?, context: Context) {
            Log.d("test", "test22222")


            val contentList = ArrayList<String>()
            contentList.add("1")
            contentList.add("2")
            contentList.add("3")

            val imageList = ArrayList<String>()
            imageList.add("1")
            imageList.add("2")
            imageList.add("3")

            val contentAdapter = ContentAdapter(contentList)
            itemBinding.contentRvDiaryDetail.adapter = contentAdapter
            itemBinding.contentRvDiaryDetail.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


            val imageAdapter = ImageAdapter(imageList)
            itemBinding.imageRvDiaryDetail.adapter = imageAdapter
            itemBinding.imageRvDiaryDetail.layoutManager =
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


class ImageAdapter(private val imageList: ArrayList<String>) :
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

        holder.bind(item)

    }


    class ImageViewHolder(private val itemImageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemImageBinding.root) {

        fun bind(item: String) {
            itemImageBinding.imageIvDiary.setImageResource(R.drawable.heart)
        }
    }
}




