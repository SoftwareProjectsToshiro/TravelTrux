package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.responseModel.ComentResponse
import com.upao.travel_trux.repositories.ComentsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComentsService(context: Context) {
    private val comentsRepository = ComentsRepository(context)

    fun getComents(context: Context, id: Int, onResult: (List<ComentResponse>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val coments = comentsRepository.getComents(context, id)
            withContext(Dispatchers.Main) {
                onResult(coments)
            }
        }
    }
}