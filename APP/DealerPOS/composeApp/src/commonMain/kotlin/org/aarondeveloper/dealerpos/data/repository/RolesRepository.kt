package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.RolesDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class RolesRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getRoles(): Flow<Resource<List<RolesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val roles = dealerDataSource.getRoles()
            emit(Resource.Success(roles))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getRolById(id: Int): Flow<Resource<RolesDto>> = flow {
        try {
            emit(Resource.Loading())
            val rol = dealerDataSource.getRolById(id)
            emit(Resource.Success(rol))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addRol(rolesDto: RolesDto): Flow<Resource<RolesDto>> = flow {
        try {
            emit(Resource.Loading())
            val rol = dealerDataSource.addRol(rolesDto)
            emit(Resource.Success(rol))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateRol(rolesDto: RolesDto): Flow<Resource<RolesDto>> = flow {
        try {
            emit(Resource.Loading())
            val rol = dealerDataSource.updateRol(rolesDto)
            emit(Resource.Success(rol))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteRol(id: Int): Flow<Resource<RolesDto>> = flow {
        try {
            emit(Resource.Loading())
            val rol = dealerDataSource.deleteRol(id)
            emit(Resource.Success(rol))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
