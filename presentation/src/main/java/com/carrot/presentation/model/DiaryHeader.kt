package com.carrot.presentation.model

import com.google.gson.annotations.SerializedName

data class DiaryHeader(
    val postId: Int,
    val title: String,
    val imageUrl: String
)
