package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class ProductosDto(
    val productoId: Int,
    val categoriaId: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Float,
    val cantidad: Int,
    val codigo: String,
    val costo: Float,
    val descuento: Float,
    val imagen: String,
    val diagarantia: String,
    val estado: String
)