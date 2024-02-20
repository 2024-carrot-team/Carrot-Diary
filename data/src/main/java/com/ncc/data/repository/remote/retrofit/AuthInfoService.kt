package com.ncc.data.repository.remote.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthInfoService {
    @POST("getConnectionInfoWithoutPassword")
    @Headers("Content-Type: application/json")
    suspend fun login(
        @Body body: String,
    ): Response<Unit>

    @POST("getConnectionInfoWithCustNo")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun signup(
        @Body body: String,
    ): Response<Unit>
}