package com.carrot.carrotdiary.model

data class Comment(
    val id: Int,
    val user: User,
    val content: String,
)