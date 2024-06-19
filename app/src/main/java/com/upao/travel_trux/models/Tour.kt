package com.upao.travel_trux.models

import java.io.Serializable

data class Tour(
    val id: Int,
    val tourPackageId: Int,
    val nombre: String,
    val descripcion: String,
    val imagen: String,
    val incluyeGuia: Boolean,
    val incluyeTransporte: Boolean,
    val horaInicio: String,
    val horaFin: String,
    val isActived: Int,
    val isDeleted: Int,
    val createdAT: String,
    val updatedAT: String
): Serializable {
}