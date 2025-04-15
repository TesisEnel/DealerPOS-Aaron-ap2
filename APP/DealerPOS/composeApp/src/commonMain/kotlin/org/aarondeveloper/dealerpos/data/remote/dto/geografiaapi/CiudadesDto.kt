package org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi

import kotlinx.serialization.Serializable

@Serializable
data class CiudadesDto(
    val ciudadId: Int,
    val estadoId: Int,
    val descripcion: String
)