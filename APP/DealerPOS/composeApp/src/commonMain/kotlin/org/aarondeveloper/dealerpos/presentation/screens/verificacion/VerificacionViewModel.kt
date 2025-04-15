package org.aarondeveloper.dealerpos.presentation.screens.verificacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.UsuariosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.VerificacionesDto
import org.aarondeveloper.dealerpos.data.repository.AjustesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.EmailSendRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.data.repository.VerificacionesRepository
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.getFechaVencimientoCodigo
import org.aarondeveloper.dealerpos.ui.languages.Language

/*----------TIPOS DE ERRORES---------|
|                                    |
|   T1 - Campos vacios               |
|   T2 - Conexion                    |
|   T3 - Usuario no identificado     |
|   T4 - Codigo Incorrecto           |
|                                    |
------------------------------------*/

class VerificacionesViewModel(
    private val usuariosRepository: UsuariosRepository,
    private val configuracionesRepository: ConfiguracionRepository,
    private val verificacionesRepository: VerificacionesRepository,
    private val ajustesRepository: AjustesRepository,
    private val emailSendRepository: EmailSendRepository,
    private val lenguajeRepository: LenguajesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getLenguajes()
        getVerificaciones()
    }

    fun validarCampos(): Boolean {
        return !_uiState.value.codigo.isNullOrBlank()
    }

    fun verificar(onNavContinuarClick: () -> Unit) {
        viewModelScope.launch {

            if (!validarCampos()) {
                _uiState.update {
                    it.copy(errorMessage = it.language!!.codigo_verificacion, verificado = false, tipoError = "T1", modal = true)
                }
                return@launch
            }

            configuracionesRepository.getUsuarioId().collect { response ->
                when (response) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(usuarioId = response.data ?: 0, cargando = false)
                        }

                        verificacionesRepository.verificarCodigo(_uiState.value.usuarioId, _uiState.value.codigo).collect { responseVerificaciones ->
                            when (responseVerificaciones) {
                                is Resource.Loading -> {
                                    _uiState.update { it.copy(errorMessage = null, cargando = true) }
                                }
                                is Resource.Success -> {
                                    _uiState.update {
                                        it.copy(cargando = false)
                                    }

                                    if(responseVerificaciones.data != 0){
                                        verificacionesRepository.cambiarEstadoVerificacion(responseVerificaciones.data ?: 0).collect { responseVerificacionesEstado ->
                                            when (responseVerificacionesEstado) {
                                                is Resource.Loading -> {
                                                    _uiState.update { it.copy(errorMessage = null, cargando = true) }
                                                }

                                                is Resource.Success -> {
                                                    _uiState.update {
                                                        it.copy(cargando = false)
                                                    }

                                                    usuariosRepository.getUsuarioById(_uiState.value.usuarioId).collect { responseUsuarios->
                                                        when (responseUsuarios) {
                                                            is Resource.Loading -> {
                                                                _uiState.update {
                                                                    it.copy(errorMessage = null, cargando = true)
                                                                }
                                                            }
                                                            is Resource.Success -> {
                                                                _uiState.update {
                                                                    it.copy(cargando = false)
                                                                }

                                                                val usuarioActualizar = UsuariosDto(
                                                                    usuarioId = responseUsuarios.data!!.usuarioId,
                                                                    rolId = responseUsuarios.data.rolId,
                                                                    paisId = responseUsuarios.data.paisId,
                                                                    estadoId = responseUsuarios.data.estadoId,
                                                                    ciudadId = responseUsuarios.data.ciudadId,
                                                                    nombre = responseUsuarios.data.nombre,
                                                                    apellido = responseUsuarios.data.apellido,
                                                                    contrasena = responseUsuarios.data.contrasena,
                                                                    sexo = responseUsuarios.data.sexo,
                                                                    telefono = responseUsuarios.data.telefono,
                                                                    email = responseUsuarios.data.email,
                                                                    direccion = responseUsuarios.data.direccion,
                                                                    imagen = responseUsuarios.data.imagen,
                                                                    verificado = "Si"
                                                                )

                                                                usuariosRepository.updateUsuario(usuarioActualizar).collect { responseUsuariosActualizar ->
                                                                    when (responseUsuariosActualizar) {
                                                                        is Resource.Loading -> {
                                                                            _uiState.update {
                                                                                it.copy(errorMessage = null, cargando = true)
                                                                            }
                                                                        }

                                                                        is Resource.Success -> {
                                                                            _uiState.update {
                                                                                it.copy(cargando = false, verificado = true)
                                                                            }
                                                                            onNavContinuarClick()
                                                                        }

                                                                        is Resource.Error -> {
                                                                            _uiState.update {
                                                                                it.copy(
                                                                                    errorMessage = it.language!!.error_actualizar_verificacion,
                                                                                    cargando = false,
                                                                                    tipoError = "T3",
                                                                                    modal = true
                                                                                )
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            is Resource.Error -> {
                                                                _uiState.update {
                                                                    it.copy(errorMessage = it.language!!.error_verificarlo, cargando = false, tipoError = "T3", modal = true)
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                is Resource.Error -> {
                                                    _uiState.update {
                                                        it.copy(errorMessage = it.language!!.error_conexion, cargando = false, tipoError = "T3", modal = true)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else{
                                        _uiState.update {
                                            it.copy(errorMessage = it.language!!.codigo_incorrecto, cargando = false, tipoError = "T4", modal = true)
                                        }
                                    }

                                }
                                is Resource.Error -> {
                                    _uiState.update {
                                        it.copy(errorMessage = it.language!!.identificacion_falta, cargando = false, tipoError = "T3", modal = true)
                                    }
                                }
                            }
                        }

                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = it.language!!.identificacion_falta, usuarioId = 0, cargando = false, tipoError = "T3", modal = true)
                        }
                    }
                }
            }

        }
    }


    private fun getVerificaciones() {
        viewModelScope.launch {
            _uiState.update { it.copy(errorMessage = null) }

            verificacionesRepository.getVerificaciones().collect { response ->
                when (response) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(verificaciones = response.data ?: emptyList(), cargando = false)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = it.language!!.error_conexion, verificaciones = emptyList(), cargando = false, tipoError = "T4", modal = true)
                        }
                    }
                }
            }
        }
    }


    private fun sendEmail() {
        viewModelScope.launch {
            _uiState.update { it.copy(errorMessage = null) }

            ajustesRepository.getAjusteById(1).collect { response ->
                when (response) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                smtpHost = response.data!!.smtpHost,
                                smtpUsername = response.data.smtpUsername,
                                smtpPassword = response.data.smtpPassword,
                                smtpPort = response.data.smtpPort,
                                cargando = false
                            )
                        }

                        configuracionesRepository.getUsuarioId().collect { responseConfiguracion ->
                            when (response) {
                                is Resource.Loading -> {
                                    _uiState.update {
                                        it.copy(
                                            errorMessage = null,
                                            cargando = true
                                        )
                                    }
                                }

                                is Resource.Success -> {
                                    _uiState.update {
                                        it.copy(usuarioId = responseConfiguracion.data ?: 0, cargando = false)
                                    }

                                    usuariosRepository.getUsuarioById(_uiState.value.usuarioId).collect { responseUsuarios ->
                                        when (responseUsuarios) {
                                            is Resource.Loading -> {
                                                _uiState.update {
                                                    it.copy(errorMessage = null, cargando = true)
                                                }
                                            }

                                            is Resource.Success -> {
                                                _uiState.update {
                                                    it.copy(cargando = false)
                                                }

                                                val codigoVerificacion = (100000..999999).random()

                                                val emailMessage = """
                                                    ${_uiState.value.language?.email_part1} ${responseUsuarios.data!!.nombre ?: ""} ${responseUsuarios.data.apellido ?: ""},
                                                    
                                                    ${_uiState.value.language?.email_part2}
                                                    
                                                    **$codigoVerificacion**
                                                    
                                                    ${_uiState.value.language?.email_part3}
                                                    
                                                    ${_uiState.value.language?.email_part4}
                                
                                                """.trimIndent()

                                                emailSendRepository.sendEmail(
                                                    _uiState.value.language!!.titulo_mensaje_email,
                                                    emailMessage,
                                                    responseUsuarios.data.email ?: "",
                                                    _uiState.value.language!!.mensaje_graciar_parte_nosotros,
                                                    response.data!!.nombreEmpresa?: "",
                                                    response.data.direccion?: "",
                                                    smtpHost = response.data.smtpHost?: "",
                                                    smtpUsername = response.data.smtpUsername?: "",
                                                    smtpPassword = response.data.smtpPassword?: "",
                                                    smtpPort = response.data.smtpPort?: 0
                                                ).collect { responseEmail ->
                                                    when (responseEmail) {
                                                        is Resource.Loading -> {
                                                            _uiState.update { it.copy(errorMessage = null, cargando = true) }
                                                        }
                                                        is Resource.Success -> {
                                                            _uiState.update {
                                                                it.copy(errorMessage = null, cargando = false, verificado = false, modal = false)
                                                            }

                                                            val fechaVencimiento: String = getFechaVencimientoCodigo()

                                                            val verificacion = VerificacionesDto(
                                                                verificacionId = 0,
                                                                usuarioId = responseUsuarios.data.usuarioId ?: 0,
                                                                codigo = codigoVerificacion.toString(),
                                                                vencimiento = fechaVencimiento,
                                                                estado = "Disponible"
                                                            )

                                                            verificacionesRepository.addVerificacion(verificacion).collect { responseVerificacion ->
                                                                when (responseVerificacion) {
                                                                    is Resource.Loading -> {
                                                                        _uiState.update { it.copy(errorMessage = null, cargando = true) }
                                                                    }
                                                                    is Resource.Success -> {
                                                                        _uiState.update {
                                                                            it.copy(errorMessage = null, cargando = false, verificado = false, modal = false)
                                                                        }
                                                                    }
                                                                    is Resource.Error -> {
                                                                        _uiState.update {
                                                                            it.copy(errorMessage = it.language!!.error_conexion, cargando = false, tipoError = "T4", modal = true)
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                        }
                                                        is Resource.Error -> {
                                                            _uiState.update {
                                                                it.copy(errorMessage = it.language!!.error_conexion, cargando = false, tipoError = "T4", modal = true)
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                            is Resource.Error -> {
                                                _uiState.update {
                                                    it.copy(errorMessage = it.language!!.identificacion_falta, cargando = false, tipoError = "T3", modal = true)
                                                }
                                            }
                                        }
                                    }
                                }

                                is Resource.Error -> {
                                    _uiState.update {
                                        it.copy(
                                            errorMessage = it.language!!.identificacion_falta,
                                            cargando = false,
                                            tipoError = "T3",
                                            modal = true
                                        )
                                    }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = it.language!!.error_conexion, cargando = false, tipoError = "T4", modal = true)
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

    fun onCodigoChange(codigo: String?) {
        _uiState.update { it.copy(codigo = codigo.toString()) }
    }

    fun onRenviarCodigoChange() {
       sendEmail()
    }

    fun onCerrarModalClick(){
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
    }

    fun onContinuarClick(onNavContinuarClick: () -> Unit) = viewModelScope.launch {
        _uiState.update { it.copy(tipoError = "", modal = false, errorMessage = "", cargando = true) }
        verificar(onNavContinuarClick)
    }

}



data class UiState(
    val verificacionId: Int = 0,
    val usuarioId: Int = 0,
    val codigo: String = "",
    val vencimiento: String = "",
    val estado: String = "",
    val verificaciones: List<VerificacionesDto> = emptyList(),
    val errorMessage: String? = null,
    val verificado: Boolean = false,
    val tipoError: String? = null,
    val modal: Boolean = false,
    val cargando: Boolean = false,
    val smtpHost: String? = "",
    val smtpUsername: String? = "",
    val smtpPassword: String? = "",
    val smtpPort: Int? = 0,
    val language: Language? = null
)


fun UiState.toEntity() = VerificacionesDto(
    verificacionId = verificacionId ?: 0,
    usuarioId = usuarioId ?: 0,
    codigo = codigo ?: "",
    vencimiento = vencimiento ?: "",
    estado = estado ?: ""
)