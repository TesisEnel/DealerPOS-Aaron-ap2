package org.aarondeveloper.dealerpos.presentation.screens.introduccion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.aarondeveloper.dealerpos.data.local.entities.ConfiguracionEntity
import org.aarondeveloper.dealerpos.data.repository.AuteticacionesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.logDebug
import org.aarondeveloper.dealerpos.librery.obtenerCodigo
import org.aarondeveloper.dealerpos.ui.languages.Language

class IntroViewModel(
    private val configuracionRepository: ConfiguracionRepository,
    private val auteticacionesRepository: AuteticacionesRepository,
    private val lenguajeRepository: LenguajesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getLenguajes()
        verificarConfiguracion()
    }

    private fun verificarConfiguracion() {
        viewModelScope.launch {
            configuracionRepository.getAllConfiguraciones().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(cargando = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { it.copy(cargando = false) }
                        if (result.data == null) {
                            val defaultConfig = ConfiguracionEntity(
                                usuarioId = 0,
                                sucursalId = 1,
                                autenticacionId = 0,
                                productoId = 1,
                            )
                            configuracionRepository.saveConfiguracion(defaultConfig)
                                .collect { resultSaveConfig ->
                                    when (resultSaveConfig) {
                                        is Resource.Loading -> {
                                            _uiState.update { it.copy(cargando = true) }
                                        }

                                        is Resource.Success -> {
                                            _uiState.update { it.copy(cargando = false) }
                                        }

                                        is Resource.Error -> {
                                            _uiState.update {
                                                it.copy(
                                                    cargando = false,
                                                    modal = true,
                                                    tipoError = "T1",
                                                    errorMessage = it.language!!.error_guardar_configuracion + ": ${resultSaveConfig.message}"
                                                )
                                            }
                                        }
                                    }
                                }
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                cargando = false,
                                modal = true,
                                tipoError = "T1",
                                errorMessage = it.language!!.error_guardar_configuracion + ": ${result.message}"
                            )
                        }
                    }
                }
            }
        }
    }

    fun verificarAuteticacion(onAutenticado: () -> Unit) {

        viewModelScope.launch {

            configuracionRepository.getUsuarioId().collect { responseConfiguracion ->
                when (responseConfiguracion) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = null,
                                cargando = true,
                                modal = false
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(errorMessage = null, cargando = false, modal = false)
                        }

                        auteticacionesRepository.verificarAutenticacion(
                            responseConfiguracion.data ?: 0, obtenerCodigo()
                        ).collect { responseAutenticacion ->
                            when (responseAutenticacion) {
                                is Resource.Loading -> {
                                    _uiState.update {
                                        it.copy(
                                            errorMessage = null,
                                            cargando = true,
                                            modal = false
                                        )
                                    }
                                }

                                is Resource.Success -> {
                                    _uiState.update {
                                        it.copy(
                                            errorMessage = null,
                                            cargando = false,
                                            modal = false
                                        )
                                    }

                                    if (responseAutenticacion.data == "Abierto") {
                                        onAutenticado()
                                    }
                                }

                                is Resource.Error -> {
                                    _uiState.update {
                                        it.copy(
                                            errorMessage = it.language!!.error_configuracion,
                                            cargando = false,
                                            modal = true,
                                            tipoError = "T1"
                                        )
                                    }
                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = it.language!!.error_configuracion,
                                cargando = false,
                                modal = true,
                                tipoError = "T1"
                            )
                        }
                    }
                }
            }
        }
    }

    fun onCerrarModalClick() {
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
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
}


data class UiState(
    val usuarioId: Int = 0,
    val sucursalId: Int = 0,
    val cargando: Boolean = false,
    val errorMessage: String? = null,
    val tipoError: String? = null,
    val modal: Boolean = false,
    val language: Language? = null
)

