package com.upao.travel_trux.models.requestModel

data class TouristRequest(
    var tour_package_id: Int,
    var user_email: String,
    var documento_identidad: String,
    var nombre: String,
    var apellido_paterno: String,
    var apellido_materno: String,
    var sexo: String,
    var tipo_documento: String,
    var fecha_salida: String,
    var fecha_nacimiento: String,
    var telefono: String,
    var email: String
) {
}