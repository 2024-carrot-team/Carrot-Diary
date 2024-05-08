package com.carrot.data.model

import com.google.gson.annotations.SerializedName

data class DailyHeaderListDTO(
    @SerializedName("data") val data: List<DailyHeaderDTO>? = null
) {
    data class DailyHeaderDTO(
        @SerializedName("content") val content: String,
        @SerializedName("diaryDate") val diaryDate: String,
        @SerializedName("postDiaryId") val postDiaryId: Int,
    )
}