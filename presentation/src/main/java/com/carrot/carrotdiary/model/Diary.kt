package com.carrot.carrotdiary.model

data class Diary(
    val id: Int?,
    val title: String,
    val user: User,
    val dailyList: List<Daily>,
    val cover: String,
    val date: String,
)