package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.responseModel.TourResponse
import com.upao.travel_trux.repositories.TourRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TourService(context: Context) {

    private val tourRepository = TourRepository(context)

    fun getTours(context: Context, id: Int, onResult: (List<TourResponse>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val tours = tourRepository.getTours(context, id)
            withContext(Dispatchers.Main) {
                onResult(tours)
            }
        }
    }
}