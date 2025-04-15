package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.DetalleProductoSucursalesDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class DetalleProductoSucursalesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getDetalleProductoSucursales(): Flow<Resource<List<DetalleProductoSucursalesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val detalleProductoSucursales = dealerDataSource.getDetalleProductoSucursales()
            emit(Resource.Success(detalleProductoSucursales))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getDetalleProductoSucursalById(id: Int): Flow<Resource<DetalleProductoSucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleProductoSucursal = dealerDataSource.getDetalleProductoSucursalById(id)
            emit(Resource.Success(detalleProductoSucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addDetalleProductoSucursal(detalleProductoSucursalDto: DetalleProductoSucursalesDto): Flow<Resource<DetalleProductoSucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleProductoSucursal = dealerDataSource.addDetalleProductoSucursal(detalleProductoSucursalDto)
            emit(Resource.Success(detalleProductoSucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateDetalleProductoSucursal(detalleProductoSucursalDto: DetalleProductoSucursalesDto): Flow<Resource<DetalleProductoSucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleProductoSucursal = dealerDataSource.updateDetalleProductoSucursal(detalleProductoSucursalDto)
            emit(Resource.Success(detalleProductoSucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteDetalleProductoSucursal(id: Int): Flow<Resource<DetalleProductoSucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleProductoSucursal = dealerDataSource.deleteDetalleProductoSucursal(id)
            emit(Resource.Success(detalleProductoSucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}

