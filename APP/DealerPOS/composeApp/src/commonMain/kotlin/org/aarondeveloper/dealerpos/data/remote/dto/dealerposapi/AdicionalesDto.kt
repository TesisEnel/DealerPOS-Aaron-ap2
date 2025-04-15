package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class AdicionalesDto(
    val adicionalId: Int,
    val productoId: Int,
    val descripcion: String,
    val precio: Float,
    val imagen: String
)