package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class CategoriasDto(
    val categoriaId: Int,
    val imagen: String,
    val descripcion: String
)
