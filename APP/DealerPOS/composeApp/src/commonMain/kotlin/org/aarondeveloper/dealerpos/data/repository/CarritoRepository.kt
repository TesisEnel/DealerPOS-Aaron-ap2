package org.aarondeveloper.dealerpos.data.repository

import org.aarondeveloper.dealerpos.data.local.dao.CarritoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.local.entities.CarritoEntity
import org.aarondeveloper.dealerpos.librery.Resource


class CarritoRepository(
    private val carritoDao: CarritoDao
) {

    fun getCarritos(): Flow<Resource<List<CarritoEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val carritos = carritoDao.getAll().firstOrNull()
            if (carritos.isNullOrEmpty()) {
                emit(Resource.Error("No hay productos en el carrito"))
            } else {
                emit(Resource.Success(carritos))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getCarritoById(id: Int): Flow<Resource<CarritoEntity?>> = flow {
        try {
            emit(Resource.Loading())
            val carrito = carritoDao.findById(id)
            emit(Resource.Success(carrito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun saveCarrito(carrito: CarritoEntity): Flow<Resource<CarritoEntity>> = flow {
        try {
            emit(Resource.Loading())
            carritoDao.save(carrito)
            emit(Resource.Success(carrito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateCarrito(carrito: CarritoEntity): Flow<Resource<CarritoEntity>> = flow {
        try {
            emit(Resource.Loading())
            carritoDao.update(carrito)
            emit(Resource.Success(carrito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteCarritoById(id: Int): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            carritoDao.delete(id)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

}
