package com.carrot.presentation.model

import java.util.Date

data class User(
    val id: String,
    val email: String,
    val password: String,
    val nickname: String,
    val birthDate: Date,
    val profile: String,
)