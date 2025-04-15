package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class DetalleVentaPagosDto(
    val detalleVentaPagoId: Int,
    val ventaId: Int,
    val usuarioId: Int,
    val metodoPago: String,
    val montoPagado: Float,
    val estado: String,
    val fecha: String
)
