package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.IconosDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class IconosRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getIconos(): Flow<Resource<List<IconosDto>>> = flow {
        try {
            emit(Resource.Loading())
            val iconos = dealerDataSource.getIconos()
            emit(Resource.Success(iconos))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getIconoById(id: Int): Flow<Resource<IconosDto>> = flow {
        try {
            emit(Resource.Loading())
            val icono = dealerDataSource.getIconoById(id)
            emit(Resource.Success(icono))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addIcono(iconosDto: IconosDto): Flow<Resource<IconosDto>> = flow {
        try {
            emit(Resource.Loading())
            val icono = dealerDataSource.addIcono(iconosDto)
            emit(Resource.Success(icono))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateIcono(iconosDto: IconosDto): Flow<Resource<IconosDto>> = flow {
        try {
            emit(Resource.Loading())
            val icono = dealerDataSource.updateIcono(iconosDto)
            emit(Resource.Success(icono))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteIcono(id: Int): Flow<Resource<IconosDto>> = flow {
        try {
            emit(Resource.Loading())
            val icono = dealerDataSource.deleteIcono(id)
            emit(Resource.Success(icono))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}

