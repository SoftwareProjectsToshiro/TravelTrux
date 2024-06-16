package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName
import com.upao.travel_trux.models.User

data class LoginResponse(
    @SerializedName("token") val msg: String,
    @SerializedName("user") val user: User
) {
}