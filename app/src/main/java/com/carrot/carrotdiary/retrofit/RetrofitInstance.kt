package com.carrot.carrotdiary.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private const val BASE_URL =
            "http://ec2-43-201-23-111.ap-northeast-2.compute.amazonaws.com:8080/"
        private var retrofitClient: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofitClient == null) {
                retrofitClient = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // API 베이스 URL 설정
                    .addConverterFactory(GsonConverterFactory.create()) // 1)
                    .build()
            }
            return retrofitClient!!
        }
    }
}