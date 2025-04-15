package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AdicionalesDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class AdicionalesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getAdicionales(): Flow<Resource<List<AdicionalesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val adicionales = dealerDataSource.getAdicionales()
            emit(Resource.Success(adicionales))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getAdicionalById(id: Int): Flow<Resource<AdicionalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val adicional = dealerDataSource.getAdicionalById(id)
            emit(Resource.Success(adicional))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addAdicional(adicionalesDto: AdicionalesDto): Flow<Resource<AdicionalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val adicional = dealerDataSource.addAdicional(adicionalesDto)
            emit(Resource.Success(adicional))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateAdicional(adicionalesDto: AdicionalesDto): Flow<Resource<AdicionalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val adicional = dealerDataSource.updateAdicional(adicionalesDto)
            emit(Resource.Success(adicional))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteAdicional(id: Int): Flow<Resource<AdicionalesDto>> = flow {
        try {
            emit(Resource.Loading())
            val adicional = dealerDataSource.deleteAdicional(id)
            emit(Resource.Success(adicional))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
