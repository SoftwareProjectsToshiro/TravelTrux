package com.upao.travel_trux.data.endpoints

import com.upao.travel_trux.models.requestModel.LoginRequest
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.models.responseModel.LoginResponse
import com.upao.travel_trux.models.responseModel.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}