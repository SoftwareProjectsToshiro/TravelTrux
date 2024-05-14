package com.upao.travel_trux.models

class User {

    var idUsuario: Long = 0
    var nombre: String = ""
    var apellido: String = ""
    var email: String = ""
    var password: String = ""
    var phone: String = ""
    var puntos: Int = 0

    constructor(idUsuario: Long, nombre: String, apellido: String, email: String, password: String, phone: String, puntos: Int) {
        this.idUsuario = idUsuario
        this.nombre = nombre
        this.apellido = apellido
        this.email = email
        this.password = password
        this.phone = phone
        this.puntos = puntos
    }

    constructor() {
    }

    override fun toString(): String {
        return "User(idUsuario=$idUsuario, nombre='$nombre', apellido='$apellido', email='$email', password='$password', phone='$phone', puntos=$puntos)"
    }
}