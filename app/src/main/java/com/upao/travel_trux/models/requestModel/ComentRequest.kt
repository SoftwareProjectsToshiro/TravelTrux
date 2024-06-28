package com.upao.travel_trux.models.requestModel

data class ComentRequest (
    val tour_package_id: Int,
    val user_id: Int,
    val content: String,
    val title: String,
    val rating: Int
){
}