package com.upao.travel_trux.models.requestModel

import java.time.LocalDate

data class ReservationRequest(
    val user_id: Int,
    val package_id: Int,
    val reservation_date: String,
    val number_of_passengers: Int,
    val payment_method: String,
    val payment_status: String,
    val status: String,
) {
}