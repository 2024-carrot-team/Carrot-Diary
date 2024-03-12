package com.carrot.presentation.model

import java.io.File

data class Daily(
    val id: Int,
    val date: String?,
    val likes: Int,
    val accidents: List<Accident>,
)


data class DailyDTO(
    val content: String?,
    val image: File?,
    )