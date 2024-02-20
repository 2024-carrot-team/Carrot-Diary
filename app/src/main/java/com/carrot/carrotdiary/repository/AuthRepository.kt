package com.carrot.carrotdiary.repository

import com.carrot.carrotdiary.DTO.SignUpResponse
import retrofit2.Call

class AuthRepository : AuthRetrofitInterface {
    override suspend fun signUp(params: HashMap<String, Any>): Call<SignUpResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun login(params: HashMap<String, Any>): Call<Unit> {
        TODO("Not yet implemented")
    }
}