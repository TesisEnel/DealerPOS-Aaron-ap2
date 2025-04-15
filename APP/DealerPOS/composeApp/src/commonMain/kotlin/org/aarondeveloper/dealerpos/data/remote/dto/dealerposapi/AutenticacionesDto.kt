package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class AutenticacionesDto(
    val autenticacionId: Int,
    val usuarioId: Int,
    val codigo: String,
    val dispositivo: String,
    val fecha: String,
    var estado: String
)
