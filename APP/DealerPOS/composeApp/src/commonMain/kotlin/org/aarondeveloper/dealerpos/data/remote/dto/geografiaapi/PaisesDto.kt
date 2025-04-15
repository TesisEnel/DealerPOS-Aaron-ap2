package org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi

import kotlinx.serialization.Serializable

@Serializable
data class PaisesDto(
    val paisId: Int,
    val descripcion: String
)