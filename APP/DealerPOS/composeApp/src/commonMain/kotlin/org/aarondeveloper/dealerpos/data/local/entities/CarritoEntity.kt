package org.aarondeveloper.dealerpos.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Carritos")
data class CarritoEntity(
    @PrimaryKey(autoGenerate = true)
    val carritoId: Int = 0,
    val usuarioId: Int,
    val productoId: Int,
    val cantidad: Int
)