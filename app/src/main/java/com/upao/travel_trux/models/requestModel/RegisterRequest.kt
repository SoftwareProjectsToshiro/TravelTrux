package com.upao.travel_trux.models.requestModel

data class RegisterRequest (
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val phone: String
){
}