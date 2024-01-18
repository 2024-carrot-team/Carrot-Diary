package com.carrot.carrotdiary.model

data class Diary(
    val id: Int?,
    val title: String,
    val user: UserInfo,
    val dailyList: List<Daily>,
    val cover: String,
    val date: String,
) {
    data class UserInfo(
        val id: String,
        val profile: String
    )
}

