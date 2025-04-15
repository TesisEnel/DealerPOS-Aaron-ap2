package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class FavoritosDto(
    val favoritoId: Int,
    val productoId: Int,
    val usuarioId: Int
)
