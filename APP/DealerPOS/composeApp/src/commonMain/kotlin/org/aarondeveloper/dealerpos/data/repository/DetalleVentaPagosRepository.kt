package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.DetalleVentaPagosDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class DetalleVentaPagosRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getDetalleVentaPagos(): Flow<Resource<List<DetalleVentaPagosDto>>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVentaPagos = dealerDataSource.getDetalleVentaPagos()
            emit(Resource.Success(detalleVentaPagos))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getDetalleVentaPagoById(id: Int): Flow<Resource<DetalleVentaPagosDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVentaPago = dealerDataSource.getDetalleVentaPagoById(id)
            emit(Resource.Success(detalleVentaPago))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addDetalleVentaPago(detalleVentaPagoDto: DetalleVentaPagosDto): Flow<Resource<DetalleVentaPagosDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVentaPago = dealerDataSource.addDetalleVentaPago(detalleVentaPagoDto)
            emit(Resource.Success(detalleVentaPago))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateDetalleVentaPago(detalleVentaPagoDto: DetalleVentaPagosDto): Flow<Resource<DetalleVentaPagosDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVentaPago = dealerDataSource.updateDetalleVentaPago(detalleVentaPagoDto)
            emit(Resource.Success(detalleVentaPago))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteDetalleVentaPago(id: Int): Flow<Resource<DetalleVentaPagosDto>> = flow {
        try {
            emit(Resource.Loading())
            val detalleVentaPago = dealerDataSource.deleteDetalleVentaPago(id)
            emit(Resource.Success(detalleVentaPago))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}

