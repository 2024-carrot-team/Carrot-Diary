package com.carrot.carrotdiary.model

import android.net.Uri

data class Diary(
    val id: Int?,
    val title: String,
    val user: String,
    val dailyList: List<Daily>,
    val cover: Uri

)
