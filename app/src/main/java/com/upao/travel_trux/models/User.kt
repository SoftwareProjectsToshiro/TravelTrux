package com.upao.travel_trux.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String
){

    override fun toString(): String {
        return "User(id=$id, nombre='$nombre', apellido='$apellido', email='$email', phone='$phone')"
    }
}