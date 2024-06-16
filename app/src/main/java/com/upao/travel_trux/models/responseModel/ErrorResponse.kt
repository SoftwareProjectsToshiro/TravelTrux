package com.upao.travel_trux.models.responseModel

import com.upao.travel_trux.models.ApiError

data class ErrorResponse(
    val errors: List<ApiError>
)