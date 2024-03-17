package com.carrot.data.remote.api

import com.carrot.data.model.DailyDTO
import com.carrot.data.model.DailyHeaderListDTO
import com.carrot.data.model.DailyIdDTO
import com.carrot.data.model.DailySeqDTO
import com.carrot.data.model.DiaryDetailDTO
import com.carrot.data.model.DiaryHeaderListDTO
import com.carrot.data.model.LoginRequest
import com.carrot.data.model.MainDTO
import com.carrot.data.model.PostUser
import com.carrot.data.model.Report
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
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
    ): Response<DiaryHeaderListDTO>

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

    @GET("diary/all/{postDiary_id}")
    suspend fun getDaily(
        @Header("Cookie") authorization: String,
        @Path("postDiary_id") postDiaryId: String,
    ): Response<DailyDTO>

    @GET("postDiary/{postDiary_id}") //24
    suspend fun getMainPageDaily(
        @Header("Cookie") authorization: String,
        @Path("postDiary_id") postDiaryId: String,
    ): Response<Any>


    @GET("main")
    suspend fun getMainPageDaily(
        @Header("Cookie") authorization: String,
    ): Response<MainDTO>


    @GET("diary/all/{postDiary_id}")
    suspend fun getPostDiary(
        @Header("Cookie") authorization: String,
        @Path("postDiary_id") postDiaryId: String,
    ): Response<DailySeqDTO>


    @GET("diary/detail/{diary_id}")
    suspend fun getDailyDetail(
        @Header("Cookie") authorization: String,
        @Path("diary_id") diaryId: String,
    ): Response<DiaryDetailDTO>


    @POST("report")
    suspend fun report(
        @Header("Cookie") authorization: String,
        @Body body: Report
    ): Response<Unit>
//    ): Response<DailyIdDTO>

}