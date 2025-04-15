package org.aarondeveloper.dealerpos.data.remote.dto.emailsendapi

import kotlinx.serialization.Serializable

@Serializable
data class EmailSendDto(
    val asunto: String,
    val mensaje: String,
    val destinatario: String,
    val piePagina: String,
    val nombreEmpresa: String,
    val direccionEmpresa: String,
    val smtpHost: String,
    val smtpUsername: String,
    val smtpPassword: String,
    val smtpPort: Int
)