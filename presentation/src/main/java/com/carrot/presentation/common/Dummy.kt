package com.carrot.presentation.common

import com.carrot.presentation.model.Accident
import com.carrot.presentation.model.MainDiaries
import com.carrot.presentation.model.Diary
import com.carrot.presentation.model.User

class Dummy {
    companion object {
        val userInfo: User = User(
            id = "ksd5715",
            email = "rltjdeh@naver.com",
            password = "12312",
            nickname = "바니바니",
            birthDate = "123",
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
        val dailyList: List<MainDiaries> = arrayListOf<MainDiaries>(
            MainDiaries(
                id = 0, date = "123", likes = 1, accidents = accidentList
            ), MainDiaries(
                id = 0, date = "123", likes = 1, accidents = accidentList
            ), MainDiaries(
                id = 0, date = "123", likes = 1, accidents = accidentList
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



