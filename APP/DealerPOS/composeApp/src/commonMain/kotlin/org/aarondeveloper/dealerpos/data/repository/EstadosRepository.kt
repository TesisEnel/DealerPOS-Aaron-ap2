package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.EstadosDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.GeografiaDataSource
import org.aarondeveloper.dealerpos.librery.Resource

class EstadosRepository(
    private val geografiaDataSource: GeografiaDataSource
) {

    fun getEstados(): Flow<Resource<List<EstadosDto>>> = flow {
        emit(Resource.Loading())
        try {
            val estados = geografiaDataSource.getEstados()
            emit(Resource.Success(estados))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun getEstadoById(id: Int): Flow<Resource<EstadosDto>> = flow {
        emit(Resource.Loading())
        try {
            val estado = geografiaDataSource.getEstadoById(id)
            emit(Resource.Success(estado))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun getEstadosByPais(paisId: Int): Flow<Resource<List<EstadosDto>>> = flow {
        emit(Resource.Loading())
        try {
            val estados = geografiaDataSource.getEstados().filter { it.paisId == paisId }
            emit(Resource.Success(estados))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun addEstado(estadosDto: EstadosDto): Flow<Resource<EstadosDto>> = flow {
        emit(Resource.Loading())
        try {
            val estado = geografiaDataSource.addEstado(estadosDto)
            emit(Resource.Success(estado))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun updateEstado(estadosDto: EstadosDto): Flow<Resource<EstadosDto>> = flow {
        emit(Resource.Loading())
        try {
            val estado = geografiaDataSource.updateEstado(estadosDto)
            emit(Resource.Success(estado))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)

    fun deleteEstado(id: Int): Flow<Resource<EstadosDto>> = flow {
        emit(Resource.Loading())
        try {
            val estado = geografiaDataSource.deleteEstado(id)
            emit(Resource.Success(estado))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }.flowOn(Dispatchers.IO)
}
