package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class ReclamacionesDto(
    val reclamacionId: Int,
    val usuarioId: Int,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
    val respuesta: String,
    val estado: String
)
