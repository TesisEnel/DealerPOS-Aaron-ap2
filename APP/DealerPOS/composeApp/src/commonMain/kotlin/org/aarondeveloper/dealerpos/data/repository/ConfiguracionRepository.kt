package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import org.aarondeveloper.dealerpos.data.local.dao.ConfiguracionDao
import org.aarondeveloper.dealerpos.data.local.entities.ConfiguracionEntity
import org.aarondeveloper.dealerpos.librery.Resource

class ConfiguracionRepository(
    private val configuracionDao: ConfiguracionDao
) {

    fun getUsuarioId(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val usuarioId = configuracionDao.getUsuarioId()
            emit(Resource.Success(usuarioId))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener el usuario ID."))
        }
    }

    fun getAutenticacionId(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val autenticacionId = configuracionDao.getAutenticacionId()
            emit(Resource.Success(autenticacionId))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener el autenticacion ID."))
        }
    }

    fun getSucursalId(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val sucursalId = configuracionDao.getSucursalId()
            emit(Resource.Success(sucursalId))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener el sucursal ID."))
        }
    }

    fun getProductoId(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val productoId = configuracionDao.getProductoId()
            emit(Resource.Success(productoId))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener el producto ID."))
        }
    }

    fun updateUsuarioId(usuarioId: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            configuracionDao.updateUsuarioId(usuarioId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Error al actualizar el usuario ID."))
        }
    }

    fun updateSucursalId(sucursalId: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            configuracionDao.updateSucursalId(sucursalId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Error al actualizar el sucursal ID."))
        }
    }

    fun updateProductoId(productoId: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            configuracionDao.updateProductoId(productoId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Error al actualizar el producto ID."))
        }
    }

    fun updateAutenticacionId(autenticacionId: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            configuracionDao.updateAutenticacionId(autenticacionId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Error al actualizar el autenticacion ID."))
        }
    }

    fun getAllConfiguraciones(): Flow<Resource<ConfiguracionEntity?>> = flow {
        emit(Resource.Loading())
        try {
            val configuracion = configuracionDao.getAll()
            emit(Resource.Success(configuracion))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener todas las configuraciones."))
        }
    }

    fun saveConfiguracion(configuracion: ConfiguracionEntity): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            configuracionDao.save(configuracion)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Error al guardar la configuración."))
        }
    }
}
