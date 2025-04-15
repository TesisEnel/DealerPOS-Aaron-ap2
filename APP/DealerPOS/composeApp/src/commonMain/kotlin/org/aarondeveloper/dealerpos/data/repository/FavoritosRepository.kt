package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.FavoritosDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class FavoritosRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getFavoritos(): Flow<Resource<List<FavoritosDto>>> = flow {
        try {
            emit(Resource.Loading())
            val favoritos = dealerDataSource.getFavoritos()
            emit(Resource.Success(favoritos))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getFavoritoById(id: Int): Flow<Resource<FavoritosDto>> = flow {
        try {
            emit(Resource.Loading())
            val favorito = dealerDataSource.getFavoritoById(id)
            emit(Resource.Success(favorito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addFavorito(favoritosDto: FavoritosDto): Flow<Resource<FavoritosDto>> = flow {
        try {
            emit(Resource.Loading())
            val favorito = dealerDataSource.addFavorito(favoritosDto)
            emit(Resource.Success(favorito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateFavorito(favoritosDto: FavoritosDto): Flow<Resource<FavoritosDto>> = flow {
        try {
            emit(Resource.Loading())
            val favorito = dealerDataSource.updateFavorito(favoritosDto)
            emit(Resource.Success(favorito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteFavorito(id: Int): Flow<Resource<FavoritosDto>> = flow {
        try {
            emit(Resource.Loading())
            val favorito = dealerDataSource.deleteFavorito(id)
            emit(Resource.Success(favorito))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
