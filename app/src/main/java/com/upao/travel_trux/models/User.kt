package com.upao.travel_trux.models

import com.google.gson.annotations.SerializedName

data class User (
    val id: Int,
    val nombre: String,
    val apellido: String,
    val email: String,
    val phone: String
){
    override fun toString(): String {
        return "User(id=$id, nombre='$nombre', apellido='$apellido', email='$email', phone='$phone')"
    }
}