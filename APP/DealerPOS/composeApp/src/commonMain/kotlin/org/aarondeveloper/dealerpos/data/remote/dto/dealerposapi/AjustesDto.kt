package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class AjustesDto(
    val ajusteId: Int? = 0,
    val nombreEmpresa: String? = "",
    val propietario: String? = "",
    val direccion: String? = "",
    val telefono: String? = "",
    val email: String? = "",
    val tipoFiscal: String? = "",
    val numeroFiscal: String? = "",
    val pais: String? = "",
    val moneda: String? = "",
    val lectora: String? = "",
    val estado: String? = "",
    val smtpHost: String? = "",
    val smtpUsername: String? = "",
    val smtpPassword: String? = "",
    val smtpPort: Int? = 0
)