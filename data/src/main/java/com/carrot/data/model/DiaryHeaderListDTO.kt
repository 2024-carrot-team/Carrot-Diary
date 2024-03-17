package com.carrot.data.model

import com.google.gson.annotations.SerializedName


data class DiaryHeaderListDTO(
    @SerializedName("data") val data: List<DiaryHeaderDTO>? = null
) {
    data class DiaryHeaderDTO(
        @SerializedName("postId") val postId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("imageUrl") val imageUrl: String
    )
}


// main.json
data class MainDTO(
    @SerializedName("data")val dataList: List<MainDiaries>,
)

data class MainDiaries(
    val memberId: Long,
    val postDiaryId: Long,
    val diaries: List<MainDiary>,
)

data class MainDiary(
    val diaryId: Int,
    val content: String,
    val imageInfo: List<ImageInfo>,
)

data class ImageInfo(
    val imageId: Long,
    val imageUrl: String,
)


// diaryallpostDiaryId.json
data class DailySeqDTO(
    val data: List<DailyOfDiaryId>,
)

data class DailyOfDiaryId(
    val memberId: Long,
    @SerializedName("diaryId")val sequenceId: Long,
    val content: String,
    val imageInfo: List<ImageInfo>,
)


// diarydetaildiaryId/ 내 일기 보기 /4(일기의 모든 것)
data class DiaryDetailDTO(
    val data: Data,
)

data class Data(
    val diaryId: Long,
    val content: String,
    val imageInfo: List<ImageInfo>,
)

