package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class CaracteristicasDto(
    val caracteristicaId: Int,
    val productoId: Int,
    val iconoId: Int,
    val nombre: String,
    val descripcion: String
)