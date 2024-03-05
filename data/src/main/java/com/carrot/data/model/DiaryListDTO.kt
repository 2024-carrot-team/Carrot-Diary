package com.carrot.data.model

import com.google.gson.annotations.SerializedName


data class DiaryListDTO(
    @SerializedName("data") val data: List<DiaryHeaderDTO>? = null
) {
    data class DiaryHeaderDTO(
        @SerializedName("postId") val postId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("imageUrl") val imageUrl: String
    )
}