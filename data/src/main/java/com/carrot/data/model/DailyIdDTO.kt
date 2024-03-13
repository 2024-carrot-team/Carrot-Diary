package com.carrot.data.model

import com.google.gson.annotations.SerializedName

data class DailyIdDTO(
    @SerializedName("memberId")
    val userId: Long,
    @SerializedName("postDiaryId")
    val postDiaryId: Long
)