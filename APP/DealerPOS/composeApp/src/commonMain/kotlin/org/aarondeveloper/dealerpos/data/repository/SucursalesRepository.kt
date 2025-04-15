package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.SucursalesDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class SucursalesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getSucursales(): Flow<Resource<List<SucursalesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val sucursales = dealerDataSource.getSucursales()
            emit(Resource.Success(sucursales))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getSucursalById(id: Int): Flow<Resource<SucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val sucursal = dealerDataSource.getSucursalById(id)
            emit(Resource.Success(sucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addSucursal(sucursalDto: SucursalesDto): Flow<Resource<SucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val sucursal = dealerDataSource.addSucursal(sucursalDto)
            emit(Resource.Success(sucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateSucursal(sucursalDto: SucursalesDto): Flow<Resource<SucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val sucursal = dealerDataSource.updateSucursal(sucursalDto)
            emit(Resource.Success(sucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteSucursal(id: Int): Flow<Resource<SucursalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val sucursal = dealerDataSource.deleteSucursal(id)
            emit(Resource.Success(sucursal))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
