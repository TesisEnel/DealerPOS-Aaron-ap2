package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.ReclamacionesDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class ReclamacionesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getReclamaciones(): Flow<Resource<List<ReclamacionesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val reclamaciones = dealerDataSource.getReclamaciones()
            emit(Resource.Success(reclamaciones))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getReclamacionById(id: Int): Flow<Resource<ReclamacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val reclamacion = dealerDataSource.getReclamacionById(id)
            emit(Resource.Success(reclamacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addReclamacion(reclamacionesDto: ReclamacionesDto): Flow<Resource<ReclamacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val reclamacion = dealerDataSource.addReclamacion(reclamacionesDto)
            emit(Resource.Success(reclamacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateReclamacion(reclamacionesDto: ReclamacionesDto): Flow<Resource<ReclamacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val reclamacion = dealerDataSource.updateReclamacion(reclamacionesDto)
            emit(Resource.Success(reclamacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteReclamacion(id: Int): Flow<Resource<ReclamacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val reclamacion = dealerDataSource.deleteReclamacion(id)
            emit(Resource.Success(reclamacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
