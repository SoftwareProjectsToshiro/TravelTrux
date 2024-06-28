package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.requestModel.ComentRequest
import com.upao.travel_trux.models.responseModel.ComentResponse
import com.upao.travel_trux.service.ComentsService

class ComentsController(context: Context) {
    private val comentsService = ComentsService(context)

    fun getComents(context: Context, id: Int, onResult: (List<ComentResponse>) -> Unit) {
        comentsService.getComents(context, id) { coments ->
            onResult(coments)
        }
    }

    fun updateComent(context: Context, coment: ComentRequest, onResult: () -> Unit) {
        comentsService.updateComent(context, coment) {
            onResult()
        }
    }
}