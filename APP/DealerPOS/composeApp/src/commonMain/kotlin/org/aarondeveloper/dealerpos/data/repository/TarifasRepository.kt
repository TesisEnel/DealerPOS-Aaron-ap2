package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.TarifasDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class TarifasRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getTarifas(): Flow<Resource<List<TarifasDto>>> = flow {
        try {
            emit(Resource.Loading())
            val tarifas = dealerDataSource.getTarifas()
            emit(Resource.Success(tarifas))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getTarifaById(id: Int): Flow<Resource<TarifasDto>> = flow {
        try {
            emit(Resource.Loading())
            val tarifa = dealerDataSource.getTarifaById(id)
            emit(Resource.Success(tarifa))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addTarifa(tarifaDto: TarifasDto): Flow<Resource<TarifasDto>> = flow {
        try {
            emit(Resource.Loading())
            val tarifa = dealerDataSource.addTarifa(tarifaDto)
            emit(Resource.Success(tarifa))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateTarifa(tarifaDto: TarifasDto): Flow<Resource<TarifasDto>> = flow {
        try {
            emit(Resource.Loading())
            val tarifa = dealerDataSource.updateTarifa(tarifaDto)
            emit(Resource.Success(tarifa))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteTarifa(id: Int): Flow<Resource<TarifasDto>> = flow {
        try {
            emit(Resource.Loading())
            val tarifa = dealerDataSource.deleteTarifa(id)
            emit(Resource.Success(tarifa))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
