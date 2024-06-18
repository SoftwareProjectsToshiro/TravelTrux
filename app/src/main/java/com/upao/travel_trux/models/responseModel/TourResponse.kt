package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName

data class TourResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("tour_package_id") val tourPackageId: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("descripcion") val description: String,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("incluye_guia") val incluyeGuia: Int,
    @SerializedName("incluye_transporte") val incluyeTransporte: Int,
    @SerializedName("hora_inicio") val hora_inicio: String,
    @SerializedName("hora_fin") val hora_fin: String,
    @SerializedName("isActived") val isActived: Int,
    @SerializedName("isDeleted") val isDeleted: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String
) {
}