package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class SucursalesDto(
    val sucursalId: Int,
    val descripcion: String
)
