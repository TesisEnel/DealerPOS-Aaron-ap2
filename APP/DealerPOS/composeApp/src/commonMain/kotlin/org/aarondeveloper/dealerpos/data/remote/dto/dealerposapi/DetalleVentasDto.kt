package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class DetalleVentasDto(
    val detalleVentaId: Int,
    val ventaId: Int,
    val productoId: Int,
    val descripcionProducto: String,
    val cantidadProducto: Int,
    val precioProducto: Float,
    val descuentoProducto: Float
)
