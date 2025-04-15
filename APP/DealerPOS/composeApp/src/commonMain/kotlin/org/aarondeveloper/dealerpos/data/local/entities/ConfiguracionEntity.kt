package org.aarondeveloper.dealerpos.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuraciones")
data class ConfiguracionEntity(
    @PrimaryKey
    val usuarioId: Int,
    val sucursalId: Int,
    val autenticacionId: Int,
    val productoId: Int
)
