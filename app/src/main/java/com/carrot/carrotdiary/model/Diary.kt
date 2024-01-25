package com.carrot.carrotdiary.model

import android.net.Uri
import java.time.LocalDateTime

data class Diary(
    //@SerializedName()
    val id: Int?,
    val title: String,
    val user: String,
    val dailyList: List<Daily>?,
    val cover: Uri?,
    val createdAt: LocalDateTime?
)
