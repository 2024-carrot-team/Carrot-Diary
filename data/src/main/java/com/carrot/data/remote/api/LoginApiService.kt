package com.carrot.data.remote.api

import com.carrot.data.model.DailyHeaderListDTO
import com.carrot.data.model.DailyIdDTO
import com.carrot.data.model.DiaryListDTO
import com.carrot.data.model.LoginRequest
import com.carrot.data.model.PostUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface LoginApiService {
    @POST("members")
    suspend fun postRegister(@Body user: PostUser): Response<Any>

    @POST("login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<String>

    @GET("post")
    suspend fun getDiary(
        @Header("Cookie") authorization: String,
    ): Response<DiaryListDTO>

    @POST("postDiary/{post_id}")
    suspend fun makeDaily(
        @Header("Cookie") authorization: String,
        @Path("post_id") postId: String,
//    ): Response<Long>
    ): Response<DailyIdDTO>

    @GET("post/{post_id}")
    suspend fun getDailyList(
        @Header("Cookie") authorization: String,
        @Path("post_id") postId: String,
    ): Response<DailyHeaderListDTO>

    @Multipart
    @POST("post")
    suspend fun postDiary(
        @Header("Cookie") authorization: String,
        @Part("title") title: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<Any>


    @Multipart
    @POST("diary/{postDiary_id}")
    suspend fun postDaily(
        @Header("Cookie") authorization: String,
        @Part("content") content: RequestBody,
        @Path("postDiary_id") postDiaryId: String,
        @Part images: List<MultipartBody.Part>
    ): Response<Any>


    @GET("postDiary/{postDiary_id}") //24
    suspend fun getMainPageDaily(
        @Header("Cookie") authorization: String,
        @Path("postDiary_id") postDiaryId: String,
    ): Response<Any>

}