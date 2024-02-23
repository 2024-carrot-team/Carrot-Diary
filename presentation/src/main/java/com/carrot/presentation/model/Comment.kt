package com.carrot.presentation.model

data class Comment(
    val id: Int,
    val user: User,
    val content: String,
)