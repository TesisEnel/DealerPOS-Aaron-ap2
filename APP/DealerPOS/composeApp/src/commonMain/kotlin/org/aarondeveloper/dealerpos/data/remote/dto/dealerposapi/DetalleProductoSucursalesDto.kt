package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class DetalleProductoSucursalesDto(
    val detalleProductoSucursalId: Int,
    val productoId: Int,
    val sucursalId: Int
)