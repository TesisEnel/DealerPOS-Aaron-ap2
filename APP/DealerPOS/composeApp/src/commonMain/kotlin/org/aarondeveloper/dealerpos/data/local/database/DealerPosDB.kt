package org.aarondeveloper.dealerpos.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.aarondeveloper.dealerpos.data.local.dao.CarritoDao
import org.aarondeveloper.dealerpos.data.local.dao.ConfiguracionDao
import org.aarondeveloper.dealerpos.data.local.dao.DetalleCarritoAdicionalDao
import org.aarondeveloper.dealerpos.data.local.entities.CarritoEntity
import org.aarondeveloper.dealerpos.data.local.entities.ConfiguracionEntity
import org.aarondeveloper.dealerpos.data.local.entities.DetalleCarritoAdicionalEntity

const val DATABASE_NAME = "dealerpos.db"

@Database(
    entities = [
        ConfiguracionEntity::class,
        CarritoEntity::class,
        DetalleCarritoAdicionalEntity::class
    ],
    version = 3,
    exportSchema = true
)
abstract class DealerPosDB : RoomDatabase() {
    abstract fun configuracionDao(): ConfiguracionDao
    abstract fun carritoDao(): CarritoDao
    abstract fun detalleCarritoAdicionalDao(): DetalleCarritoAdicionalDao
}