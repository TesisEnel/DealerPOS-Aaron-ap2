package org.aarondeveloper.dealerpos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import org.aarondeveloper.dealerpos.data.local.entities.ConfiguracionEntity

@Dao
interface ConfiguracionDao {

    @Upsert
    suspend fun save(configuracion: ConfiguracionEntity)

    @Query("SELECT usuarioId FROM Configuraciones LIMIT 1")
    suspend fun getUsuarioId(): Int

    @Query("SELECT sucursalId FROM Configuraciones LIMIT 1")
    suspend fun getSucursalId(): Int

    @Query("SELECT autenticacionId FROM Configuraciones LIMIT 1")
    suspend fun getAutenticacionId(): Int

    @Query("SELECT productoId FROM Configuraciones LIMIT 1")
    suspend fun getProductoId(): Int

    @Query("UPDATE Configuraciones SET usuarioId = :usuarioId")
    suspend fun updateUsuarioId(usuarioId: Int)

    @Query("UPDATE Configuraciones SET sucursalId = :sucursalId")
    suspend fun updateSucursalId(sucursalId: Int)

    @Query("UPDATE Configuraciones SET autenticacionId = :autenticacionId")
    suspend fun updateAutenticacionId(autenticacionId: Int)

    @Query("UPDATE Configuraciones SET productoId = :productoId")
    suspend fun updateProductoId(productoId: Int)

    @Delete
    suspend fun delete(configuracion: ConfiguracionEntity)

    @Query("SELECT * FROM Configuraciones LIMIT 1")
    suspend fun getAll(): ConfiguracionEntity?

}
