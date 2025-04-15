package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AutenticacionesDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.getFechaActual

class AuteticacionesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getAutenticaciones(): Flow<Resource<List<AutenticacionesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val autenticaciones = dealerDataSource.getAutenticaciones()
            emit(Resource.Success(autenticaciones))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun getAutenticacionById(id: Int): Flow<Resource<AutenticacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val autenticacion = dealerDataSource.getAutenticacionById(id)
            emit(Resource.Success(autenticacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun addAutenticacion(autenticacionDto: AutenticacionesDto): Flow<Resource<AutenticacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val autenticacion = dealerDataSource.addAutenticacion(autenticacionDto)
            emit(Resource.Success(autenticacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun updateAutenticacion(autenticacionDto: AutenticacionesDto): Flow<Resource<AutenticacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val autenticacion = dealerDataSource.updateAutenticacion(autenticacionDto)
            emit(Resource.Success(autenticacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun updateEstado(id: Int, estado: String): Flow<Resource<AutenticacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val autenticacion = dealerDataSource.getAutenticacionById(id)
            autenticacion.estado = estado
            val autenticacionActualizada = dealerDataSource.updateAutenticacion(autenticacion)

            emit(Resource.Success(autenticacionActualizada))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }


    fun deleteAutenticacion(id: Int): Flow<Resource<AutenticacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val autenticacion = dealerDataSource.deleteAutenticacion(id)
            emit(Resource.Success(autenticacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun verificarAutenticacion(usuarioId: Int, codigo: String): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val autenticaciones = dealerDataSource.getAutenticaciones().filter {
                it.usuarioId == usuarioId && it.codigo == codigo
            }

            val fechaActual = getFechaActual()
            var autenticacionEncontrada = false

            for (autenticacion in autenticaciones) {
                if (autenticacion.estado == "Abierto" && fechaActual <= autenticacion.fecha) {
                    emit(Resource.Success(autenticacion.estado))
                    autenticacionEncontrada = true
                    return@flow
                }
            }

            if (!autenticacionEncontrada) {
                emit(Resource.Success("Ninguno"))
            }

        } catch (e: Exception) {
            emit(Resource.Error("Error al verificar autenticación"))
        }
    }



}
