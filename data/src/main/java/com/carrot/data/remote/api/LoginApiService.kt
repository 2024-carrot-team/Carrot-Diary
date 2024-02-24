package com.carrot.data.remote.api

import com.carrot.data.model.LoginRequest
import com.carrot.data.model.PostUser
import com.carrot.data.model.WriteDiary
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface LoginApiService {
    @POST("members")
    suspend fun postRegister(@Body user: PostUser): Response<Any>

    @POST("login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<String>


    //    @GET("post")
//    suspend fun getDiary(@Header("Cookie") cookie: String): Response<Any>
    @GET("post")
    suspend fun getDiary(): Response<Any>

//    @POST("post")
//    suspend fun postDiary(@Body writeDiary: WriteDiary): Response<Any>

    @Multipart
    @POST("post")
    suspend fun postDiary(
        @Part title: MultipartBody.Part,
        @Part image: MultipartBody.Part
    ): Response<Any>
}