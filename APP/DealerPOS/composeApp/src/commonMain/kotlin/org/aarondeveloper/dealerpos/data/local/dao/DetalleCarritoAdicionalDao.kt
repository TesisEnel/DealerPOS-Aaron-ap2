package org.aarondeveloper.dealerpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.aarondeveloper.dealerpos.data.local.entities.CarritoEntity
import org.aarondeveloper.dealerpos.data.local.entities.DetalleCarritoAdicionalEntity

@Dao
interface DetalleCarritoAdicionalDao {
    @Upsert
    suspend fun save(detalleCarritoAdicionales: DetalleCarritoAdicionalEntity)

    @Update
    suspend fun update(detalleCarritoAdicionales: DetalleCarritoAdicionalEntity)

    @Query("SELECT * FROM DetalleCarritoAdicionales WHERE detalleCarritoAdicionalId = :id LIMIT 1")
    suspend fun findById(id: Int): DetalleCarritoAdicionalEntity?

    @Delete
    suspend fun delete(detalleCarritoAdicionales: DetalleCarritoAdicionalEntity)

    @Query("DELETE FROM DetalleCarritoAdicionales WHERE detalleCarritoAdicionalId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM DetalleCarritoAdicionales")
    fun getAll(): Flow<List<DetalleCarritoAdicionalEntity>>

}