package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.DetalleVentasDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class DetalleVentasRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getDetalleVentas(): Flow<Resource<List<DetalleVentasDto>>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVentas = dealerDataSource.getDetalleVentas()
            emit(Resource.Success(detalleVentas))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getDetalleVentaById(id: Int): Flow<Resource<DetalleVentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVenta = dealerDataSource.getDetalleVentaById(id)
            emit(Resource.Success(detalleVenta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addDetalleVenta(detalleVentaDto: DetalleVentasDto): Flow<Resource<DetalleVentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVenta = dealerDataSource.addDetalleVenta(detalleVentaDto)
            emit(Resource.Success(detalleVenta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateDetalleVenta(detalleVentaDto: DetalleVentasDto): Flow<Resource<DetalleVentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVenta = dealerDataSource.updateDetalleVenta(detalleVentaDto)
            emit(Resource.Success(detalleVenta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteDetalleVenta(id: Int): Flow<Resource<DetalleVentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVenta = dealerDataSource.deleteDetalleVenta(id)
            emit(Resource.Success(detalleVenta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}

