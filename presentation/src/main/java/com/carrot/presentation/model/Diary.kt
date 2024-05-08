package com.carrot.presentation.model

data class Diary(
    val id: Int?,
    val title: String,
    val user: User,
    val dailyList: List<MainDiaries>,
    val cover: String,
    val date: String,
)
