package com.upao.travel_trux.data.endpoints

import com.upao.travel_trux.models.requestModel.TouristRequest
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.ComentRequest
import com.upao.travel_trux.models.requestModel.LoginRequest
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.models.requestModel.ReservationRequest
import com.upao.travel_trux.models.responseModel.LoginResponse
import com.upao.travel_trux.models.responseModel.ApiResponse
import com.upao.travel_trux.models.responseModel.ComentResponse
import com.upao.travel_trux.models.responseModel.TourPackageResponse
import com.upao.travel_trux.models.responseModel.TourResponse
import com.upao.travel_trux.models.responseModel.TouristResponse
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
    suspend fun registerTourist(@Body touristRequest: TouristRequest): Response<ApiResponse>

    @GET("tourist/{doc}")
    suspend fun getTourist(@Path("doc") doc: String): Response<TouristResponse>

    @POST("reservations")
    suspend fun createReservation(@Body reservation: ReservationRequest): Response<ApiResponse>

    @GET("reservations/{id}")
    suspend fun reservationGet(@Path("id") id: Int): Response<TourResponse>

    @POST("comments")
    suspend fun comment(@Body comment: ComentRequest): Response<ApiResponse>

    @GET("comments/{user_id}")
    suspend fun getComents(@Path("user_id") id: Int): Response<List<ComentResponse>>

    @PUT("comments/{id}")
    suspend fun updateComent(@Path("id") id: Int, @Body comment: ComentRequest): Response<ApiResponse>
}