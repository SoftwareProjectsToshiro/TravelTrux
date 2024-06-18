package com.upao.travel_trux.models

data class TourPackage(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val tipoPaquete: String,
    val precio: Double,
    val imagen: String,
    val isActived: Int,
    val isDeleted: Int,
    val createdAT: String,
    val updatedAT: String,
    val tours: List<Tour>
){
}