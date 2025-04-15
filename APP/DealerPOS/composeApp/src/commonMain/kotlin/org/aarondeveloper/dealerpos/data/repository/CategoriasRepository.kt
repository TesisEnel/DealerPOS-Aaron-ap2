package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CategoriasDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class CategoriasRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getCategorias(): Flow<Resource<List<CategoriasDto>>> = flow {
        try {
            emit(Resource.Loading())
            val categorias = dealerDataSource.getCategorias()
            emit(Resource.Success(categorias))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getCategoriaById(id: Int): Flow<Resource<CategoriasDto>> = flow {
        try {
            emit(Resource.Loading())
            val categoria = dealerDataSource.getCategoriaById(id)
            emit(Resource.Success(categoria))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addCategoria(categoriaDto: CategoriasDto): Flow<Resource<CategoriasDto>> = flow {
        try {
            emit(Resource.Loading())
            val categoria = dealerDataSource.addCategoria(categoriaDto)
            emit(Resource.Success(categoria))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateCategoria(categoriaDto: CategoriasDto): Flow<Resource<CategoriasDto>> = flow {
        try {
            emit(Resource.Loading())
            val categoria = dealerDataSource.updateCategoria(categoriaDto)
            emit(Resource.Success(categoria))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteCategoria(id: Int): Flow<Resource<CategoriasDto>> = flow {
        try {
            emit(Resource.Loading())
            val categoria = dealerDataSource.deleteCategoria(id)
            emit(Resource.Success(categoria))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}

