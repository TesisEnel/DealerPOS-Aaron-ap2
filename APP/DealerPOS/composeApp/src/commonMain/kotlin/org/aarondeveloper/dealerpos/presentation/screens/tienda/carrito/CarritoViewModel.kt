package org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.aarondeveloper.dealerpos.data.repository.AuteticacionesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.obtenerCodigo
import org.aarondeveloper.dealerpos.ui.languages.Language


/*----------TIPOS DE ERRORES---------|
|                                    |
|   T1 - Usuario no identificado     |
|   T2 - Conexion                    |
------------------------------------*/

class CarritoViewModel(
    private val configuracionesRepository: ConfiguracionRepository,
    private val auteticacionesRepository: AuteticacionesRepository,
    private val usuariosRepository: UsuariosRepository,
    private val lenguajeRepository: LenguajesRepository,

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
                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
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
    val errorMessage: String? = null,
    val tipoError: String? = null,
    val modal: Boolean = false,
    val cargando: Boolean = false,
    val language: Language? = null,
)