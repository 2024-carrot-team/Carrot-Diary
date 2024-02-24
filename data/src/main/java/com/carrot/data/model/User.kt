package com.carrot.data.model

import com.google.gson.annotations.SerializedName

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

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
)