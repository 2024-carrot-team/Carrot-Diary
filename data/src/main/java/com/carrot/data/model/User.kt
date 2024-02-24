package com.carrot.data.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class User(
    val id: String,
    val email: String,
    val password: String,
    val nickname: String,
    val birthDate: String,
    val profile: String?,
)


data class PostUser(
    val email: String,
    val password: String,
    val nickname: String,
    val birthDayTime: String,
    val role: String,
)


data class LoginRequest(
    val email: String,
    val password: String
)

data class WriteDiary(
    val title: String,
    val image: File
)

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
)