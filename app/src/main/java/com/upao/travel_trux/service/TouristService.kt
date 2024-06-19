package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.Tourist
import com.upao.travel_trux.repositories.TouristRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TouristService(context: Context) {

    private val touristRepository = TouristRepository(context)

    fun registerTourist(context: Context, tourist: Tourist, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = touristRepository.registerTourist(context, tourist)
            withContext(Dispatchers.Main) {
                onResult(isSuccess)
            }
        }
    }
}