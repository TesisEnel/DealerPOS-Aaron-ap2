package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.VentasDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class VentasRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getVentas(): Flow<Resource<List<VentasDto>>> = flow {
        try {
            emit(Resource.Loading())
            val ventas = dealerDataSource.getVentas()
            emit(Resource.Success(ventas))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getVentaById(id: Int): Flow<Resource<VentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val venta = dealerDataSource.getVentaById(id)
            emit(Resource.Success(venta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addVenta(ventaDto: VentasDto): Flow<Resource<VentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val venta = dealerDataSource.addVenta(ventaDto)
            emit(Resource.Success(venta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateVenta(ventaDto: VentasDto): Flow<Resource<VentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val venta = dealerDataSource.updateVenta(ventaDto)
            emit(Resource.Success(venta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteVenta(id: Int): Flow<Resource<VentasDto>> = flow {
        try {
            emit(Resource.Loading())
            val venta = dealerDataSource.deleteVenta(id)
            emit(Resource.Success(venta))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
