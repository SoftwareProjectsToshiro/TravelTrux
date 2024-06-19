package com.upao.travel_trux.models

data class Tourist(
    var user_email: String,
    var dni: String,
    var nombre: String,
    var apellido_paterno: String,
    var apellido_materno: String,
    var sexo: String,
//    var typeDocument: String,
    var fecha_nacimiento: String,
    var telefono: String,
    var email: String
) {
}