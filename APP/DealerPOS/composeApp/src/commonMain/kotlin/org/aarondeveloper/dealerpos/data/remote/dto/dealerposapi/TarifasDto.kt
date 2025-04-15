package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class TarifasDto(
    val tarifaId: Int,
    val sucursalId: Int,
    val ciudadId: Int,
    val monto: Float
)
