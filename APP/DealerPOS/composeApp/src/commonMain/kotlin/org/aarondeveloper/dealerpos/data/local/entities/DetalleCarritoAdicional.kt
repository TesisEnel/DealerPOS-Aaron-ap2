package org.aarondeveloper.dealerpos.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DetalleCarritoAdicionales")
data class DetalleCarritoAdicionalEntity(
    @PrimaryKey(autoGenerate = true)
    val detalleCarritoAdicionalId: Int = 0,
    val carritoId: Int,
    val productoId: Int,
    val adicionalId: Int,
    val cantidad: Int
)
