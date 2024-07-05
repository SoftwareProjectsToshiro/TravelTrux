package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.requestModel.ReservationRequest
import com.upao.travel_trux.service.ReservationService

class ReservationController(context: Context) {
    private val reservationService = ReservationService(context)

    fun createReservation(context: Context, reservation: ReservationRequest, onResult: (String) -> Unit) {
        reservationService.createReservation(context, reservation) { reservationID ->
            onResult(reservationID)
        }
    }
}