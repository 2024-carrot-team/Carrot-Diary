package com.carrot.presentation.model

import com.carrot.data.model.ImageInfo
import com.google.gson.annotations.SerializedName

data class MainData(
    val dataList: List<MainDiary>,
)

data class MainDiary(
    val memberId: Long,
    val postDiaryId: Long,
    val diaries: List<MainDaily>,
) {
    data class MainDaily(
        val diaryId: Int,
        val content: String,
        val imageInfo: List<ImageInfo>,
    )
}




