package com.carrot.carrotdiary.repository

import com.carrot.carrotdiary.DTO.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthRetrofitInterface {

    @Headers("accept: application/json", "content-type: application/json")
    @POST("api/members")
    suspend fun signUp(@Body params: HashMap<String, Any>): Call<SignUpResponse>

    @Headers("accept: application/json", "content-type: application/json")
    @POST("api/login")
    suspend fun login(@Body params: HashMap<String, Any>): Call<Unit>
}