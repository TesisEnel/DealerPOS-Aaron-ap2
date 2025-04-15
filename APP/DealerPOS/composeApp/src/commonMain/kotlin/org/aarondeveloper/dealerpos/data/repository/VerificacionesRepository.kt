package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.VerificacionesDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.getFechaActual
import org.aarondeveloper.dealerpos.librery.validarFecha

class VerificacionesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getVerificaciones(): Flow<Resource<List<VerificacionesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val verificaciones = dealerDataSource.getVerificaciones()
            emit(Resource.Success(verificaciones))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun getVerificacionById(id: Int): Flow<Resource<VerificacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val verificacion = dealerDataSource.getVerificacionById(id)
            emit(Resource.Success(verificacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun addVerificacion(verificacionesDto: VerificacionesDto): Flow<Resource<VerificacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val verificacion = dealerDataSource.addVerificacion(verificacionesDto)
            emit(Resource.Success(verificacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun updateVerificacion(verificacionesDto: VerificacionesDto): Flow<Resource<VerificacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val verificacion = dealerDataSource.updateVerificacion(verificacionesDto)
            emit(Resource.Success(verificacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun deleteVerificacion(id: Int): Flow<Resource<VerificacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val verificacion = dealerDataSource.deleteVerificacion(id)
            emit(Resource.Success(verificacion))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }


    fun verificarCodigo(usuarioId: Int, codigo: String): Flow<Resource<Int>> = flow {
        try {
            emit(Resource.Loading())
            val verificaciones = dealerDataSource.getVerificaciones()
            val verificacionesUsuario = verificaciones.filter { it.usuarioId == usuarioId }

            if (verificacionesUsuario.isNotEmpty()) {
                var codigoValido = false
                var verificacionId = 0

                for (verificacion in verificacionesUsuario) {
                    when (verificacion.estado) {
                        "Disponible" -> {
                            val fechaActual = getFechaActual()
                            val noVencido = validarFecha(verificacion.vencimiento ?: "", fechaActual)

                            if (noVencido) {
                                if (verificacion.codigo == codigo) {
                                    codigoValido = true
                                    verificacionId = verificacion.verificacionId ?: 0
                                    break
                                }
                            } else {
                                emit(Resource.Error("El código ha expirado"))
                            }
                        }

                        else -> {
                            continue
                        }
                    }
                }

                if (codigoValido) {
                    emit(Resource.Success(verificacionId))
                } else {
                    emit(Resource.Success(0))
                }

            } else {
                emit(Resource.Error("No se encontró una verificación para el usuario"))
            }

        } catch (e: Exception) {
            emit(Resource.Error("Error al verificar el código: ${e.message}"))
        }
    }


    fun cambiarEstadoVerificacion(id: Int): Flow<Resource<VerificacionesDto>> = flow {
        try {
            emit(Resource.Loading())
            val verificacion = dealerDataSource.getVerificacionById(id)
            val verificacionActualizada = verificacion.copy(estado = "Usado")
            val updatedVerificacion = dealerDataSource.updateVerificacion(verificacionActualizada)

            emit(Resource.Success(updatedVerificacion))

        } catch (e: Exception) {
            emit(Resource.Error("Error al cambiar el estado"))
        }
    }

}
