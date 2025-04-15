package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class VentasDto(
    val ventaId: Int,
    val usuarioId: Int,
    val paisId: Int,
    val provinciaId: Int,
    val ciudadId: Int,
    val numeroPedido: String,
    val tipoVenta: String,
    val nombre: String,
    val telefono: String,
    val email: String,
    val descuento: Float,
    val impuesto: Float,
    val envio: String,
    val subtotal: Float,
    val metodoEntrega: String,
    val direccion: String,
    val estadoEntrega: String,
    val estadoPagado: String,
    val nota: String,
    val fecha: String
)
