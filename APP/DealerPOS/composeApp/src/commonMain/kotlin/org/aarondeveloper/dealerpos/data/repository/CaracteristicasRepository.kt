package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CaracteristicasDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class CaracteristicasRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getCaracteristicas(): Flow<Resource<List<CaracteristicasDto>>> = flow {
        try {
            emit(Resource.Loading())
            val caracteristicas = dealerDataSource.getCaracteristicas()
            emit(Resource.Success(caracteristicas))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getCaracteristicaById(id: Int): Flow<Resource<CaracteristicasDto>> = flow {
        try {
            emit(Resource.Loading())
            val caracteristica = dealerDataSource.getCaracteristicaById(id)
            emit(Resource.Success(caracteristica))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addCaracteristica(caracteristicasDto: CaracteristicasDto): Flow<Resource<CaracteristicasDto>> = flow {
        try {
            emit(Resource.Loading())
            val caracteristica = dealerDataSource.addCaracteristica(caracteristicasDto)
            emit(Resource.Success(caracteristica))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateCaracteristica(caracteristicasDto: CaracteristicasDto): Flow<Resource<CaracteristicasDto>> = flow {
        try {
            emit(Resource.Loading())
            val caracteristica = dealerDataSource.updateCaracteristica(caracteristicasDto)
            emit(Resource.Success(caracteristica))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteCaracteristica(id: Int): Flow<Resource<CaracteristicasDto>> = flow {
        try {
            emit(Resource.Loading())
            val caracteristica = dealerDataSource.deleteCaracteristica(id)
            emit(Resource.Success(caracteristica))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
