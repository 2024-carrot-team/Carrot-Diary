package com.carrot.data.model

import com.google.gson.annotations.SerializedName

data class DailyDTO(
    @SerializedName("data") val data: List<AccidentDTO>? = null
) {
    data class AccidentDTO(
        @SerializedName("memberId") val memberId: Int,
        @SerializedName("diaryId") val diaryId: Int,
        @SerializedName("content") val content: String,
        @SerializedName("imageInfo") val imageInfo: List<ImageInfo>,
    ) {
        data class ImageInfo(
            @SerializedName("imageId") val imageInfo: String,
            @SerializedName("imageUrl") val imageUrl: String,
        )
    }
}