package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName

data class TourPackageResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("descripcion") val description: String,
    @SerializedName("tipo_paquete") val packageType: String,
    @SerializedName("precio") val price: Double,
    @SerializedName("imagen") val image: String,
    @SerializedName("isActived") val isActived: Int,
    @SerializedName("isDeleted") val isDeleted: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String
) {
}