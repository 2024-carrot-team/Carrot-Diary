package com.carrot.carrotdiary.model

import android.net.Uri

data class Accident(
    val id: Int,
    val content: String,
    val imageList: List<Uri>,
    val user: User
)