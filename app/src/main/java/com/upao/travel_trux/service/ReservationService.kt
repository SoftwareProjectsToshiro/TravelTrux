package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.requestModel.ReservationRequest
import com.upao.travel_trux.repositories.ReservationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationService(context: Context) {
    private val reservationRepository = ReservationRepository(context)

    fun createReservation(context: Context, reservationRequest: ReservationRequest, onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val reservationID = reservationRepository.createReservation(context, reservationRequest)
            withContext(Dispatchers.Main) {
                onResult(reservationID)
            }
        }
    }
}