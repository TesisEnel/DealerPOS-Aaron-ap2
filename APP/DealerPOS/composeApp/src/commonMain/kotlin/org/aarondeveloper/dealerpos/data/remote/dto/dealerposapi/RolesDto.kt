package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class RolesDto(
    val rolId: Int,
    val nombre: String,
    val descripcion: String
)
