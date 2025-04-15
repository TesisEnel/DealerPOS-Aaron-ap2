package org.aarondeveloper.dealerpos.data.repository

import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.PaisesDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.GeografiaDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.aarondeveloper.dealerpos.librery.Resource

class PaisesRepository(
    private val geografiaDataSource: GeografiaDataSource
) {

    fun getPaises(): Flow<Resource<List<PaisesDto>>> = flow {
        emit(Resource.Loading())
        try {
            val paises = withContext(Dispatchers.IO) {
                geografiaDataSource.getPaises()
            }
            emit(Resource.Success(paises))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun getPaisById(id: Int): Flow<Resource<PaisesDto>> = flow {
        emit(Resource.Loading())
        try {
            val pais = withContext(Dispatchers.IO) {
                geografiaDataSource.getPaisById(id)
            }
            emit(Resource.Success(pais))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun addPais(paisesDto: PaisesDto): Flow<Resource<PaisesDto>> = flow {
        emit(Resource.Loading())
        try {
            val pais = withContext(Dispatchers.IO) {
                geografiaDataSource.addPais(paisesDto)
            }
            emit(Resource.Success(pais))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun updatePais(paisesDto: PaisesDto): Flow<Resource<PaisesDto>> = flow {
        emit(Resource.Loading())
        try {
            val pais = withContext(Dispatchers.IO) {
                geografiaDataSource.updatePais(paisesDto)
            }
            emit(Resource.Success(pais))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun deletePais(id: Int): Flow<Resource<PaisesDto>> = flow {
        emit(Resource.Loading())
        try {
            val pais = withContext(Dispatchers.IO) {
                geografiaDataSource.deletePais(id)
            }
            emit(Resource.Success(pais))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)
}
