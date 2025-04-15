package org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi

import kotlinx.serialization.Serializable

@Serializable
data class UsuariosDto(
    val usuarioId: Int? = 0,
    val rolId: Int? = 0,
    val paisId: Int? = 0,
    val estadoId: Int? = 0,
    val ciudadId: Int? = 0,
    val nombre: String? = "",
    val apellido: String? = "",
    val contrasena: String? = "",
    val sexo: String? = "",
    val telefono: String? = "",
    val email: String? = "",
    val direccion: String? = "",
    val imagen: String? = "",
    val verificado: String? = ""
)
