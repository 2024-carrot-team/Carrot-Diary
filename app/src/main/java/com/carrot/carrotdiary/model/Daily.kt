package com.carrot.carrotdiary.model


import java.util.Date

data class Daily(
    val id: Int,
    val date: Date?,
    val likes: Int,
    val accidents: List<Accident>,
)
