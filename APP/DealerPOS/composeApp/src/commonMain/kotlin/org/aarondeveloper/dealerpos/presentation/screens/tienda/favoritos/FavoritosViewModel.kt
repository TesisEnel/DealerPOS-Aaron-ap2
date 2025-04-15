package org.aarondeveloper.dealerpos.presentation.screens.tienda.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.aarondeveloper.dealerpos.data.local.dao.DetalleCarritoAdicionalDao
import org.aarondeveloper.dealerpos.data.local.entities.CarritoEntity
import org.aarondeveloper.dealerpos.data.local.entities.DetalleCarritoAdicionalEntity
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AdicionalesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CaracteristicasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CategoriasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.FavoritosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.IconosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.ProductosDto
import org.aarondeveloper.dealerpos.data.repository.AdicionalesRepository
import org.aarondeveloper.dealerpos.data.repository.AuteticacionesRepository
import org.aarondeveloper.dealerpos.data.repository.CaracteristicasRepository
import org.aarondeveloper.dealerpos.data.repository.CarritoRepository
import org.aarondeveloper.dealerpos.data.repository.CategoriasRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.DetalleCarritoAdicionalRepository
import org.aarondeveloper.dealerpos.data.repository.DetalleProductoSucursalesRepository
import org.aarondeveloper.dealerpos.data.repository.FavoritosRepository
import org.aarondeveloper.dealerpos.data.repository.IconosRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.ProductosRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.librery.CATEGORIATODOS
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.logDebug
import org.aarondeveloper.dealerpos.librery.obtenerCodigo
import org.aarondeveloper.dealerpos.ui.languages.Language


/*----------TIPOS DE ERRORES---------|
|                                    |
|   T1 - Usuario no identificado     |
|   T2 - Conexion                    |
|                                    |
------------------------------------*/

class FavoritosViewModel(
    private val configuracionesRepository: ConfiguracionRepository,
    private val auteticacionesRepository: AuteticacionesRepository,
    private val usuariosRepository: UsuariosRepository,
    private val productosRepository: ProductosRepository,
    private val lenguajeRepository: LenguajesRepository,
    private val favoritosRepository: FavoritosRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getLenguajes()
    }

    fun verificarAuteticacion(onNoAutenticado: () -> Unit){

        viewModelScope.launch {

            configuracionesRepository.getUsuarioId().collect { responseConfiguracion ->
                when (responseConfiguracion) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false, estadoBusqueda = it.language!!.buscando_articulos) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(errorMessage = null, cargando = false, modal = false)
                        }

                        auteticacionesRepository.verificarAutenticacion(responseConfiguracion.data ?: 0, obtenerCodigo()).collect { responseAutenticacion ->
                            when (responseAutenticacion) {
                                is Resource.Loading -> {
                                    _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                }
                                is Resource.Success -> {
                                    _uiState.update {
                                        it.copy(errorMessage = null, cargando = false, modal = false)
                                    }

                                    if (responseAutenticacion.data == "Abierto"){
                                        getUsuario(responseConfiguracion.data ?: 0)
                                    }
                                    else{
                                        onNoAutenticado()
                                    }
                                }
                                is Resource.Error -> {
                                    _uiState.update { it.copy(errorMessage = responseConfiguracion.message, cargando = false, modal = true, tipoError = "T2") }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        onNoAutenticado()
                    }
                }
            }
        }
    }

    fun getUsuario(usuarioId: Int){
        viewModelScope.launch {
            usuariosRepository.getUsuarioById(usuarioId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(cargando = true, errorMessage = null)
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(usuarioId = resource.data!!.usuarioId, cargando = true, errorMessage = null)
                        }
                        getFavoritos(resource.data!!.usuarioId ?: 0)
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = it.language!!.identificacion_falta, tipoError = "T2", modal = true, cargando = false)
                        }
                    }
                }
            }
        }
    }


    fun getFavoritos(usuarioId: Int) {
        viewModelScope.launch {
            favoritosRepository.getFavoritos().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(cargando = true, estadoBusqueda = it.language!!.buscando_articulos) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(cargando = true) }

                        val productosEnFavoritos = result.data?.filter { it.usuarioId == usuarioId } ?: emptyList()

                        if(productosEnFavoritos.isEmpty()){
                            _uiState.update { it.copy(cargando = false, estadoBusqueda = it.language!!.favoritos_vacio) }
                        }
                        else{
                            _uiState.update { it.copy(favoritos = productosEnFavoritos) }

                            val listaProductos = mutableListOf<ProductosDto>()

                            productosEnFavoritos.forEach { favorito ->
                                productosRepository.getProductoById(favorito.productoId).collect { resultProducto ->
                                    when (resultProducto) {
                                        is Resource.Loading -> {
                                            _uiState.update { it.copy(cargando = true, estadoBusqueda = it.language!!.buscando_articulos) }
                                        }
                                        is Resource.Success -> {
                                            resultProducto.data?.let { producto ->
                                                listaProductos.add(producto)
                                                _uiState.update { it.copy(productos = listaProductos.toList()) }
                                            }
                                        }
                                        is Resource.Error -> {
                                            _uiState.update {
                                                it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2"
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEliminarFavoritoChange(favoritoId: Int){

        viewModelScope.launch {
            favoritosRepository.deleteFavorito(favoritoId).collect { resultFavoritoEliminar ->
                when (resultFavoritoEliminar) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(cargando = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(cargando = false)
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = it.language!!.error_conexion,
                                cargando = false,
                                modal = true,
                                tipoError = "T2"
                            )
                        }
                    }
                }
            }
        }
    }


    private fun getLenguajes() {
        viewModelScope.launch {
            verificarIdioma()

            while (isActive) {
                delay(5000)
                verificarIdioma()
            }
        }
    }

    private suspend fun verificarIdioma() {
        lenguajeRepository.getLenguages().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(cargando = true) }
                }
                is Resource.Success -> {
                    _uiState.update { it.copy(cargando = false, language = result.data as? Language) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(errorMessage = result.message, cargando = false, modal = true, tipoError = "T1") }
                }
            }
        }
    }

    fun onCerrarModalClick(){
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
    }

}


data class UiState(
    val usuarioId: Int? = 0,
    val productoId: Int? = 0,
    val errorMessage: String? = null,
    val estadoBusqueda: String = "...",
    val tipoError: String? = null,
    val modal: Boolean = false,
    val cargando: Boolean = false,
    val language: Language? = null,
    val favoritos: List<FavoritosDto>? = emptyList(),
    val productos: List<ProductosDto>? = emptyList(),
)