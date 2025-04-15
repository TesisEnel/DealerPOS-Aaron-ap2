package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class IconosDto(
    val iconoId: Int,
    val titulo: String,
    val imagen: String
)
