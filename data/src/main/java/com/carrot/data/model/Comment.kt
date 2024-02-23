package com.carrot.data.model

data class Comment(
    val id: Int,
    val user: User,
    val content: String,
)