package com.carrot.data.remote.api

import com.carrot.presentation.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("members")
    suspend fun postRegister(@Body user: User): Response<String>

}