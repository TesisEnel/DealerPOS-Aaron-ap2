package org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi

import kotlinx.serialization.Serializable

@Serializable
data class EstadosDto(
    val estadoId: Int,
    val paisId: Int,
    val descripcion: String
)