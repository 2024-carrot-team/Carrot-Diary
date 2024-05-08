package com.carrot.presentation.view.auth.signup

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val passwordCheck: String = "",
    val nickname: String = "",
    val birthDate: String = "1990-01-01T00:00:00",
)
