package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName

data class TouristResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("tipo_documento") val tipoDocumento: String,
    @SerializedName("documento_identidad") val documentoIdentidad: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido_paterno") val apellidoPaterno: String,
    @SerializedName("apellido_materno") val apellidoMaterno: String,
    @SerializedName("sexo") val sexo: String,
    @SerializedName("email") val email: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("fecha_nacimiento") val fechaNacimiento: String
) {

}