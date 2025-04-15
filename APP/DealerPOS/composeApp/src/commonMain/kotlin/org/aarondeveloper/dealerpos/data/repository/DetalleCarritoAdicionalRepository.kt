package org.aarondeveloper.dealerpos.data.repository

import org.aarondeveloper.dealerpos.data.local.dao.DetalleCarritoAdicionalDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.local.entities.DetalleCarritoAdicionalEntity
import org.aarondeveloper.dealerpos.librery.Resource

class DetalleCarritoAdicionalRepository(
    private val detalleCarritoAdicionalesDao: DetalleCarritoAdicionalDao
) {

    fun getDetallesCarritoAdicional(): Flow<Resource<List<DetalleCarritoAdicionalEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val detalleCarritoAdicionales = detalleCarritoAdicionalesDao.getAll().firstOrNull()
            if (detalleCarritoAdicionales.isNullOrEmpty()) {
                emit(Resource.Error("Conexión fallida"))
            } else {
                emit(Resource.Success(detalleCarritoAdicionales))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getDetalleCarritoAdicionalById(id: Int): Flow<Resource<DetalleCarritoAdicionalEntity?>> = flow {
        try {
            emit(Resource.Loading())
            val detalle = detalleCarritoAdicionalesDao.findById(id)
            emit(Resource.Success(detalle))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun saveDetalleCarritoAdicional(detalle: DetalleCarritoAdicionalEntity): Flow<Resource<DetalleCarritoAdicionalEntity>> = flow {
        try {
            emit(Resource.Loading())
            detalleCarritoAdicionalesDao.save(detalle)
            emit(Resource.Success(detalle))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateDetalleCarritoAdicional(detalle: DetalleCarritoAdicionalEntity): Flow<Resource<DetalleCarritoAdicionalEntity>> = flow {
        try {
            emit(Resource.Loading())
            detalleCarritoAdicionalesDao.update(detalle)
            emit(Resource.Success(detalle))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteDetalleCarritoAdicionalById(id: Int): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            detalleCarritoAdicionalesDao.delete(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
