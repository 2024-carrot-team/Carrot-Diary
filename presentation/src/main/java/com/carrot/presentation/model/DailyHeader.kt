package com.carrot.presentation.model

data class DailyHeader(
    val content: String,
    val diaryDate: String,
    val postDiaryId: Int,



) {
    val year = diaryDate.split("T")[0].split("-")[0] + "년"
    val month = diaryDate.split("T")[0].split("-")[1] + "월"
    val day = diaryDate.split("T")[0].split("-")[2] + "일"
    val monthDay = month + day
    val yearMonth = year + month
    val yearMonthDay = year + month + day
}