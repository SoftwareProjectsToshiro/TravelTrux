package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("msg") val msg: String
) {
}