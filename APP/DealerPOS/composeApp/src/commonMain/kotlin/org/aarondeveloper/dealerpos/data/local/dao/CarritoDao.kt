package org.aarondeveloper.dealerpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.aarondeveloper.dealerpos.data.local.entities.CarritoEntity

@Dao
interface CarritoDao {
    @Upsert
    suspend fun save(carrito: CarritoEntity)

    @Update
    suspend fun update(carrito: CarritoEntity)

    @Query("SELECT * FROM Carritos WHERE carritoId = :id LIMIT 1")
    suspend fun findById(id: Int): CarritoEntity?

    @Query("DELETE FROM Carritos WHERE carritoId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM Carritos")
    fun getAll(): Flow<List<CarritoEntity>>

}