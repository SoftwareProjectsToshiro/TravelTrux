package com.upao.travel_trux.models.adaptersModel

import com.upao.travel_trux.models.responseModel.TourResponse
import java.io.Serializable

class TripAdapterModel(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var imagen: String,
    var precio: Double
): Serializable {
}