package com.carrot.data.model


// Accident id는 daily의 List<Accident>의 인덱스로 사용 해도 될 듯..?
data class Accident(
    val id: Int,
    val content: String,
    val imageList: List<String>,
    val date: String
)