package com.carrot.data.model


import java.io.File

data class Daily(
    val id: Int,
    val date: String?,
    val likes: Int,
    val accidents: List<Accident>,
)