package com.carrot.carrotdiary.view.main.profile

import androidx.lifecycle.ViewModel
import com.carrot.carrotdiary.model.Accident
import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.model.Diary
import java.text.SimpleDateFormat
import java.util.Locale

class ProfileViewModel : ViewModel() {
    val currentTime: Long = System.currentTimeMillis()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    private val dateFormatString = SimpleDateFormat("yyyy-MM-dd")
    private val localDate = dateFormat.parse(currentTime.toString())
    private val userInfo =
        Diary.UserInfo(id = "ksd5715", profile = "https://source.unsplash.com/random")
    private val accidentImageList = arrayListOf<String>(
        "https://source.unsplash.com/random",
        "https://source.unsplash.com/random"
    )
    private val accidentList = arrayListOf<Accident>(
        Accident(
            id = 0,
            content = "오늘 날씨 최고",
            imageList = accidentImageList,
            date = "2024년01월24일11시21분"
        ),
        Accident(
            id = 0,
            content = "오늘 날씨 최고",
            imageList = accidentImageList,
            date = "2024년01월24일11시21분"
        ),
        Accident(
            id = 0,
            content = "오늘 날씨 최고",
            imageList = accidentImageList,
            date = "2024년01월24일11시21분"
        )
    )
    private val dailyList = arrayListOf<Daily>(
        Daily(
            id = 0,
            date = localDate,
            likes = 1,
            accidents = accidentList
        ),
        Daily(
            id = 0,
            date = localDate,
            likes = 1,
            accidents = accidentList
        ),
        Daily(
            id = 0,
            date = localDate,
            likes = 1,
            accidents = accidentList
        )
    )
    val diaryList = Diary(
        id = 1,
        title = "당신의 근심을 적는 일기 ",
        user = userInfo,
        dailyList = dailyList,
        cover = "https://source.unsplash.com/random",
        date = "2024년01월24일11시21분",
    )
}