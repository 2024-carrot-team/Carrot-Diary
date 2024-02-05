package com.carrot.carrotdiary.view.main.profile

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carrot.carrotdiary.R
import com.carrot.carrotdiary.databinding.ItemThumbnailDiaryBinding
import com.carrot.carrotdiary.model.Accident
import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.model.Diary
import com.carrot.carrotdiary.model.User
import java.text.SimpleDateFormat
import java.util.Locale


class DiaryListAdapter() :
    RecyclerView.Adapter<DiaryListAdapter.ViewHolder>() {
    val currentTime: Long = System.currentTimeMillis()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    private val dateFormatString = SimpleDateFormat("yyyy-MM-dd")
    private val localDate = dateFormat.parse("2023-02-10")


    private val userInfo =
        User(
            id = "ksd5715",
            email = "rltjdeh@naver.com",
            password = "12312",
            nickname = "바니바니",
            birthDate = localDate,
            profile = "https://source.unsplash.com/random"
        )
    private val accidentImageList = arrayListOf<String>(
        "https://source.unsplash.com/random", "https://source.unsplash.com/random"
    )
    private val accidentList = arrayListOf<Accident>(
        Accident(
            id = 0, content = "오늘 날씨 최고", imageList = accidentImageList, date = "2024년01월24일11시21분"
        ), Accident(
            id = 0, content = "오늘 날씨 최고", imageList = accidentImageList, date = "2024년01월24일11시21분"
        ), Accident(
            id = 0, content = "오늘 날씨 최고", imageList = accidentImageList, date = "2024년01월24일11시21분"
        )
    )
    private val dailyList = arrayListOf<Daily>(
        Daily(
            id = 0, date = localDate, likes = 1, accidents = accidentList
        ), Daily(
            id = 0, date = localDate, likes = 1, accidents = accidentList
        ), Daily(
            id = 0, date = localDate, likes = 1, accidents = accidentList
        )
    )
    private val diaryList = arrayListOf<Diary>(
        Diary(
            id = 1,
            title = "당신의 근심을 적는 일기 ",
            user = userInfo,
            dailyList = dailyList,
            cover = "https://source.unsplash.com/random/cat",
            date = "2024년01월24일11시21분",
        ), Diary(
            id = 1,
            title = "당신의 근심을 적는 일기 ",
            user = userInfo,
            dailyList = dailyList,
            cover = "https://source.unsplash.com/random/cat",
            date = "2024년01월24일11시21분",
        ), Diary(
            id = 1,
            title = "당신의 근심을 적는 일기 ",
            user = userInfo,
            dailyList = dailyList,
            cover = "https://source.unsplash.com/random/cat",
            date = "2024년01월24일11시21분",
        ), Diary(
            id = 1,
            title = "당신의 근심을 적는 일기 ",
            user = userInfo,
            dailyList = dailyList,
            cover = "https://source.unsplash.com/random/dog",
            date = "2024년01월24일11시21분",
        ), Diary(
            id = 1,
            title = "당신의 근심을 적는 일기 ",
            user = userInfo,
            dailyList = dailyList,
            cover = "https://source.unsplash.com/random/cat",
            date = "2024년01월24일11시21분",
        )
    )

    class GridAdapter(val layout: View) : RecyclerView.ViewHolder(layout)

    private val items = diaryList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thumbnail_diary, parent, false)
        return ViewHolder(ItemThumbnailDiaryBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.diaryTitle.text = items[position].title
        Glide.with(holder.itemView).load(Uri.parse(items[position].cover))
            .into(holder.thumbnailImage)
//        holder.thumbnailImage.setImageURI(Uri.parse(items[position].cover))
//        holder.thumbnailImage
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(binding: ItemThumbnailDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        val thumbnailImage = binding.imageViewThumbnailItemThumbnailDiary
        val diaryTitle = binding.textViewContentItemThumbnailDiary
    }
}
