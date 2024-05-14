package com.upao.travel_trux.handlerSQLite

class TABLES {

    // Tabla de Usuarios
    val TABLE_USER = "usuarios"
    val USER_ID = "idUsuario"
    val USER_NAME = "nombre"
    val USER_LASTNAME = "apellido"
    val USER_EMAIL = "email"
    val USER_PASSWORD = "password"
    val USER_PHONE = "phone"
    val USER_POINTS = "puntos"

    val CREATE_TABLE_USER = ("CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT,"
            + USER_LASTNAME + " TEXT,"
            + USER_EMAIL + " TEXT,"
            + USER_PASSWORD + " TEXT,"
            + USER_PHONE + " TEXT,"
            + USER_POINTS + " INTEGER"
            + ")")


    // Tabla de Paquetes_Turisticos
    val TABLE_PAQUETES = "paquetes_turisticos"
    val PAQUETE_ID = "idPaquete"
    val PAQUETE_NOMBRE = "nombre"
    val PAQUETE_DESCRIPCION = "descripcion"
    val PAQUETE_PRECIO = "precio"
    val PAQUETE_DISPONIBILIDAD = "disponibilidad"
    val PAQUETE_DESTINO = "destino"

    val CREATE_TABLE_PAQUETES = ("CREATE TABLE " + TABLE_PAQUETES + "("
            + PAQUETE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PAQUETE_NOMBRE + " TEXT,"
            + PAQUETE_DESCRIPCION + " TEXT,"
            + PAQUETE_PRECIO + " REAL,"
            + PAQUETE_DISPONIBILIDAD + " INTEGER,"
            + PAQUETE_DESTINO + " TEXT"
            + ")")


    // Tabla de Reservas
    val TABLE_RESERVAS = "reservas"
    val RESERVA_ID = "idReserva"
    val RESERVA_ID_USUARIO = "idUsuario"
    val RESERVA_ID_PAQUETE = "idPaquete"
    val RESERVA_FECHA = "fechaReserva"
    val RESERVA_CANTIDAD = "cantidadPersona"
    val RESERVA_ESTADO = "estado"

    val CREATE_TABLE_RESERVAS = ("CREATE TABLE " + TABLE_RESERVAS + "("
            + RESERVA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RESERVA_ID_USUARIO + " INTEGER,"
            + RESERVA_ID_PAQUETE + " INTEGER,"
            + RESERVA_FECHA + " TEXT,"
            + RESERVA_CANTIDAD + " INTEGER,"
            + RESERVA_ESTADO + " TEXT,"
            + "FOREIGN KEY (" + RESERVA_ID_USUARIO + ") REFERENCES " + TABLE_USER + "(" + USER_ID + "),"
            + "FOREIGN KEY (" + RESERVA_ID_PAQUETE + ") REFERENCES " + TABLE_PAQUETES + "(" + PAQUETE_ID + ")"
            + ")")



}