package com.carrot.data.model

import com.google.gson.annotations.SerializedName

data class DailyIdDTO(
    @SerializedName("postDiaryId")
    val postDiaryId: Long
)