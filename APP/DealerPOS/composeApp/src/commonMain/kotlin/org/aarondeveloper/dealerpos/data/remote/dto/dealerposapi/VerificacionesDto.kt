package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class VerificacionesDto(
    val verificacionId: Int? = 0,
    val usuarioId: Int? = 0,
    val codigo: String? = "",
    val vencimiento: String? = "",
    val estado: String? = ""
)
