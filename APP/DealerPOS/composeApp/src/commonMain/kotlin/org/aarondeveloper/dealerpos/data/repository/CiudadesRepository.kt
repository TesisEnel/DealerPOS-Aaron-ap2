package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.CiudadesDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.GeografiaDataSource
import org.aarondeveloper.dealerpos.librery.Resource

class CiudadesRepository(
    private val geografiaDataSource: GeografiaDataSource
) {

    fun getCiudades(): Flow<Resource<List<CiudadesDto>>> = flow {
        emit(Resource.Loading())
        try {
            val ciudades = geografiaDataSource.getCiudades()
            emit(Resource.Success(ciudades))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun getCiudadById(id: Int): Flow<Resource<CiudadesDto>> = flow {
        emit(Resource.Loading())
        try {
            val ciudad = geografiaDataSource.getCiudadById(id)
            emit(Resource.Success(ciudad))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun getCiudadesByEstado(estadoId: Int): Flow<Resource<List<CiudadesDto>>> = flow {
        emit(Resource.Loading())
        try {
            val ciudades = geografiaDataSource.getCiudades().filter { it.estadoId == estadoId }
            emit(Resource.Success(ciudades))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun addCiudad(ciudadesDto: CiudadesDto): Flow<Resource<CiudadesDto>> = flow {
        emit(Resource.Loading())
        try {
            val ciudad = geografiaDataSource.addCiudad(ciudadesDto)
            emit(Resource.Success(ciudad))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun updateCiudad(ciudadesDto: CiudadesDto): Flow<Resource<CiudadesDto>> = flow {
        emit(Resource.Loading())
        try {
            val ciudad = geografiaDataSource.updateCiudad(ciudadesDto)
            emit(Resource.Success(ciudad))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun deleteCiudad(id: Int): Flow<Resource<CiudadesDto>> = flow {
        emit(Resource.Loading())
        try {
            val ciudad = geografiaDataSource.deleteCiudad(id)
            emit(Resource.Success(ciudad))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)
}
