package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName

data class ReservationResponseId(
    @SerializedName("reservation_id") val reservation_id: String
) {
}