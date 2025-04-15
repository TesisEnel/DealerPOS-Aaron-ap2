package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.ProductosDto
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource

class ProductosRepository(
    private val dealerDataSource: DealerDataSource
) {

    fun getProductos(): Flow<Resource<List<ProductosDto>>> = flow {
        try {
            emit(Resource.Loading())
            val productos = dealerDataSource.getProductos()
            emit(Resource.Success(productos))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun getProductoById(id: Int): Flow<Resource<ProductosDto>> = flow {
        try {
            emit(Resource.Loading())
            val producto = dealerDataSource.getProductoById(id)
            emit(Resource.Success(producto))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun addProducto(productoDto: ProductosDto): Flow<Resource<ProductosDto>> = flow {
        try {
            emit(Resource.Loading())
            val producto = dealerDataSource.addProducto(productoDto)
            emit(Resource.Success(producto))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun updateProducto(productoDto: ProductosDto): Flow<Resource<ProductosDto>> = flow {
        try {
            emit(Resource.Loading())
            val producto = dealerDataSource.updateProducto(productoDto)
            emit(Resource.Success(producto))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }

    fun deleteProducto(id: Int): Flow<Resource<ProductosDto>> = flow {
        try {
            emit(Resource.Loading())
            val producto = dealerDataSource.deleteProducto(id)
            emit(Resource.Success(producto))
        } catch (e: Exception) {
            emit(Resource.Error("Conexión fallida"))
        }
    }
}
