package com.carrot.carrotdiary.DTO

data class SignUpDTO(
    val email: String,
    val password: String,
    val nickname: String,
    val birthDayTime: String,
    val role: String
) : SignUpResponse

interface SignUpResponse {
}