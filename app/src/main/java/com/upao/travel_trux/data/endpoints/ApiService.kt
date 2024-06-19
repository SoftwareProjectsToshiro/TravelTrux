package com.upao.travel_trux.data.endpoints

import com.upao.travel_trux.models.Tourist
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.LoginRequest
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.models.responseModel.LoginResponse
import com.upao.travel_trux.models.responseModel.ApiResponse
import com.upao.travel_trux.models.responseModel.TourPackageResponse
import com.upao.travel_trux.models.responseModel.TourResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ApiResponse>


    @GET("user/{email}")
    suspend fun userGet(@Path("email") email: String): Response<User>

    @PUT("user/{id}")
    suspend fun userUpdate(@Path("id") id: Int, @Body user:User): Response<ApiResponse>

    @GET("packages")
    suspend fun packagesGet(): Response<List<TourPackageResponse>>

    @GET("packages/{id}/tour")
    suspend fun toursGet(@Path("id") id: Int): Response<List<TourResponse>>

    @POST("tourist")
    suspend fun registerTourist(@Body tourist: Tourist): Response<ApiResponse>
}