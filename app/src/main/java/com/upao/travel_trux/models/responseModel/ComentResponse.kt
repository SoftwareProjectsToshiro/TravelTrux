package com.upao.travel_trux.models.responseModel

import com.google.gson.annotations.SerializedName

data class ComentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("tour_package_id") val tourPackageId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("title") val titulo: String,
    @SerializedName("content") val descripcion: String,
    @SerializedName("rating") val rating: Int
) {
}