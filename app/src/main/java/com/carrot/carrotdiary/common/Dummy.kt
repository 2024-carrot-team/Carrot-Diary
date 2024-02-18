package com.carrot.carrotdiary.common

import com.carrot.carrotdiary.model.Accident
import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.model.Diary
import com.carrot.carrotdiary.model.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Dummy {
    companion object {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
        val localDate: Date = dateFormat.parse("2023-02-10")
        val userInfo: User = User(
            id = "ksd5715",
            email = "rltjdeh@naver.com",
            password = "12312",
            nickname = "바니바니",
            birthDate = localDate,
            profile = "https://source.unsplash.com/random"
        )
        val accidentImageList: List<String> = arrayListOf<String>(
            "https://source.unsplash.com/random/cat", "https://source.unsplash.com/category/nature"
        )
        val accidentList: List<Accident> = arrayListOf<Accident>(
            Accident(
                id = 0,
                content = "오늘 날씨 최고",
                imageList = accidentImageList,
                date = "2024년01월24일11시21분"
            ), Accident(
                id = 1, content = "아 배고파", imageList = accidentImageList, date = "2024년01월24일11시21분"
            ), Accident(
                id = 2, content = "오점뭐", imageList = accidentImageList, date = "2024년01월24일11시21분"
            ), Accident(
                id = 3,
                content = "코틀린 미만잡",
                imageList = accidentImageList,
                date = "2024년01월24일11시21분"
            )
        )
        val dailyList: List<Daily> = arrayListOf<Daily>(
            Daily(
                id = 0, date = localDate, likes = 1, accidents = accidentList
            ), Daily(
                id = 0, date = localDate, likes = 1, accidents = accidentList
            ), Daily(
                id = 0, date = localDate, likes = 1, accidents = accidentList
            )
        )
        val diaryList: List<Diary> = arrayListOf<Diary>(
            Diary(
                id = 0,
                title = "당신의 근심을 적는 일기 ",
                user = userInfo,
                dailyList = dailyList,
                cover = "https://source.unsplash.com/random",
                date = "2024년01월24일11시21분",
            ), Diary(
                id = 1,
                title = "오늘도 수고했던 하루 ",
                user = userInfo,
                dailyList = dailyList,
                cover = "https://source.unsplash.com/random",
                date = "2024년02월24일11시21분",
            ), Diary(
                id = 2,
                title = "배부른 하루 ",
                user = userInfo,
                dailyList = dailyList,
                cover = "https://source.unsplash.com/random",
                date = "2024년01월24일11시21분",
            ), Diary(
                id = 3,
                title = "피곤했던 하루 ",
                user = userInfo,
                dailyList = dailyList,
                cover = "https://source.unsplash.com/random",
                date = "2024년01월24일11시21분",
            ), Diary(
                id = 4,
                title = "즐거웠던 하루  ",
                user = userInfo,
                dailyList = dailyList,
                cover = "https://source.unsplash.com/random",
                date = "2024년01월24일11시21분",
            )
        )
    }
}



