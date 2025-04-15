package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AjustesDto
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.logDebug

class AjustesRepository(
    private val ajustesDataSource: DealerDataSource
) {

    fun getAjustes(): Flow<Resource<List<AjustesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val ajustes = ajustesDataSource.getAjustes()
            emit(Resource.Success(ajustes))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun getAjusteById(id: Int): Flow<Resource<AjustesDto>> = flow {
        try {
            emit(Resource.Loading())
            val ajuste = ajustesDataSource.getAjusteById(id)
            logDebug("Correcto: $ajuste")
            emit(Resource.Success(ajuste))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
            logDebug("Fallido: " + e.message.toString())
        }
    }

    fun addAjuste(ajusteDto: AjustesDto): Flow<Resource<AjustesDto>> = flow {
        try {
            emit(Resource.Loading())
            val ajuste = ajustesDataSource.addAjuste(ajusteDto)
            emit(Resource.Success(ajuste))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun updateAjuste(ajusteDto: AjustesDto): Flow<Resource<AjustesDto>> = flow {
        try {
            emit(Resource.Loading())
            val ajuste = ajustesDataSource.updateAjuste(ajusteDto)
            emit(Resource.Success(ajuste))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }

    fun deleteAjuste(id: Int): Flow<Resource<AjustesDto>> = flow {
        try {
            emit(Resource.Loading())
            val ajuste = ajustesDataSource.deleteAjuste(id)
            emit(Resource.Success(ajuste))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión Fallida"))
        }
    }
}
