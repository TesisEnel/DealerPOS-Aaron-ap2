package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.UsuariosDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource
import org.aarondeveloper.dealerpos.librery.Resource

class UsuariosRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getUsuarios(): Flow<Resource<List<UsuariosDto>>> = flow {
        try {
            emit(Resource.Loading())
            val usuarios = dealerDataSource.getUsuarios()
            emit(Resource.Success(usuarios))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun getUsuarioById(id: Int): Flow<Resource<UsuariosDto>> = flow {
        try {
            emit(Resource.Loading())
            val usuario = dealerDataSource.getUsuarioById(id)
            emit(Resource.Success(usuario))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun addUsuario(usuarioDto: UsuariosDto): Flow<Resource<UsuariosDto>> = flow {
        try {
            emit(Resource.Loading())
            val usuario = dealerDataSource.addUsuario(usuarioDto)
            emit(Resource.Success(usuario))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun updateUsuario(usuarioDto: UsuariosDto): Flow<Resource<UsuariosDto>> = flow {
        try {
            emit(Resource.Loading())
            val usuario = dealerDataSource.updateUsuario(usuarioDto)
            emit(Resource.Success(usuario))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }

    fun deleteUsuario(id: Int): Flow<Resource<UsuariosDto>> = flow {
        try {
            emit(Resource.Loading())
            val usuario = dealerDataSource.deleteUsuario(id)
            emit(Resource.Success(usuario))
        } catch (e: Exception) {
            emit(Resource.Error("Conexion Fallida"))
        }
    }
}
