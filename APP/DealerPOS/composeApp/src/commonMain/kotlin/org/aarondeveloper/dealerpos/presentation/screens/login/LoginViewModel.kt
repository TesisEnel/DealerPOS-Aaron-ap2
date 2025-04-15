package org.aarondeveloper.dealerpos.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AutenticacionesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.UsuariosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.VerificacionesDto
import org.aarondeveloper.dealerpos.data.repository.AjustesRepository
import org.aarondeveloper.dealerpos.data.repository.AuteticacionesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.EmailSendRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.data.repository.VerificacionesRepository
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.getFechaVencimientoCodigo
import org.aarondeveloper.dealerpos.librery.obtenerCodigo
import org.aarondeveloper.dealerpos.librery.obtenerDispositivo
import org.aarondeveloper.dealerpos.ui.languages.Language

/*----------TIPOS DE ERRORES---------|
|                                    |
|   T1 - Campos Vacio                |
|   T2 - Conexion                    |
|   T3 - U/C Incorrectos             |
|   T4 - No verificado               |
|                                    |
------------------------------------*/

class LoginViewModel(
    private val usuariosRepository: UsuariosRepository,
    private val configuracionesRepository: ConfiguracionRepository,
    private val auteticacionesRepository: AuteticacionesRepository,
    private val emailSendRepository: EmailSendRepository,
    private val ajustesRepository: AjustesRepository,
    private val verificacionesRepository: VerificacionesRepository,
    private val lenguajeRepository: LenguajesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getLenguajes()
    }

    fun validarCampos(): Boolean {
        return !_uiState.value.email.isNullOrBlank() &&
                !_uiState.value.contrasena.isNullOrBlank()
    }

    private fun iniciarSesion(onAutenticado: () -> Unit) {
        viewModelScope.launch {

            if (!validarCampos()) {
                _uiState.update {
                    it.copy(errorMessage = it.language!!.error_campos_vacios, verificado = false, tipoError = "T1", modal = true)
                }
                return@launch
            }

            usuariosRepository.getUsuarios().collect { responseGetUsuario ->
                when (responseGetUsuario) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true) }
                    }

                    is Resource.Success -> {
                        val usuarios = responseGetUsuario.data ?: emptyList()
                        _uiState.update { it.copy(usuarios = usuarios, cargando = true) }

                        val existe = usuarios.firstOrNull { it.email == _uiState.value.email && it.contrasena == _uiState.value.contrasena }
                        if (existe != null) {
                            if(existe.verificado == "Si"){
                                _uiState.update { it.copy(usuarioId = existe.usuarioId ?: 0)}

                                configuracionesRepository.updateUsuarioId(_uiState.value.usuarioId).collect { responseConfiguracion ->
                                    when (responseConfiguracion) {
                                        is Resource.Loading -> {
                                            _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                        }
                                        is Resource.Success -> {
                                            _uiState.update {
                                                it.copy(errorMessage = null, cargando = false, modal = false)
                                            }

                                            val nuevaAutenticacion = AutenticacionesDto(
                                                autenticacionId = 0,
                                                usuarioId = existe.usuarioId ?: 0,
                                                codigo = obtenerCodigo(),
                                                dispositivo = obtenerDispositivo(),
                                                fecha = getFechaVencimientoCodigo(),
                                                estado = "Abierto",
                                            )

                                            auteticacionesRepository.addAutenticacion(nuevaAutenticacion).collect { responseAutenticacion ->
                                                when (responseAutenticacion) {
                                                    is Resource.Loading -> {
                                                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                                    }
                                                    is Resource.Success -> {
                                                        _uiState.update {
                                                            it.copy(errorMessage = null, cargando = false, modal = false)
                                                        }

                                                        configuracionesRepository.updateAutenticacionId(responseAutenticacion.data!!.autenticacionId).collect { responseConfiguracionAutenticacion ->
                                                            when (responseConfiguracionAutenticacion) {
                                                                is Resource.Loading -> {
                                                                    _uiState.update {
                                                                        it.copy(errorMessage = null, cargando = true, modal = false)
                                                                    }
                                                                }
                                                                is Resource.Success -> {
                                                                    _uiState.update { it.copy(errorMessage = null, cargando = false, modal = false) }
                                                                    onAutenticado()
                                                                }
                                                                is Resource.Error -> {
                                                                    _uiState.update { it.copy(errorMessage = responseConfiguracionAutenticacion.message, cargando = false, modal = true, tipoError = "T2") }
                                                                }
                                                            }
                                                        }

                                                    }
                                                    is Resource.Error -> {
                                                        _uiState.update { it.copy(errorMessage = it.language!!.error_verificacion_codigo, cargando = false, modal = true, tipoError = "T2") }
                                                    }
                                                }
                                            }

                                        }
                                        is Resource.Error -> {
                                            _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2") }
                                        }
                                    }
                                }
                            }
                            else{
                                _uiState.update { it.copy(errorMessage = it.language!!.error_cuenta_no_verificada, verificado = false, tipoError = "T4", modal = true) }
                            }
                        }
                        else{
                            _uiState.update { it.copy(errorMessage = it.language!!.error_correo_o_contrasena, tipoError = "T3", modal = true) }
                            return@collect
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, usuarios = emptyList(), cargando = false, tipoError = "T2", modal = true) }
                    }
                }
            }
        }
    }

    fun verificarAuteticacion(onAutenticado: () -> Unit){

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
                                        onAutenticado()
                                    }

                                }
                                is Resource.Error -> {
                                    _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2") }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_configuracion, cargando = false, modal = true, tipoError = "T2") }
                    }
                }
            }
        }
    }

    fun verificarCuenta(onVerificacion: () -> Unit){
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

                        usuariosRepository.getUsuarioById(responseConfiguracion.data ?: 0).collect { responseGetUsuario ->
                            when (responseGetUsuario) {
                                is Resource.Loading -> {
                                    _uiState.update { it.copy(errorMessage = null, cargando = true) }
                                }

                                is Resource.Success -> {
                                    _uiState.update { it.copy(cargando = true) }
                                    sendEmail(responseGetUsuario.data!!.email ?: "", responseGetUsuario, onVerificacion)
                                }
                                is Resource.Error -> {
                                    _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2") }
                                }
                            }
                        }

                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_configuracion, cargando = false, modal = true, tipoError = "T2") }
                    }
                }
            }
        }
    }


    private fun sendEmail(email: String, nuevoUsuario: Resource<UsuariosDto>, onVerificacion: () -> Unit) {
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

                        val codigoVerificacion = (100000..999999).random()

                        val emailMessage = """
                            ${_uiState.value.language?.email_part1} ${nuevoUsuario.data!!.nombre ?: ""} ${nuevoUsuario.data.apellido ?: ""},
                            
                            ${_uiState.value.language?.email_part2}
                            
                            **$codigoVerificacion**
                            
                            ${_uiState.value.language?.email_part3}
                            
                            ${_uiState.value.language?.email_part4}
        
                        """.trimIndent()


                        emailSendRepository.sendEmail(
                            _uiState.value.language!!.titulo_mensaje_email,
                            emailMessage,
                            email,
                            _uiState.value.language!!.mensaje_graciar_parte_nosotros,
                            response.data!!.nombreEmpresa ?: "",
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
                                        it.copy(errorMessage = null, cargando = true, modal = false)
                                    }

                                    val fechaVencimiento: String = getFechaVencimientoCodigo()

                                    val verificacion = VerificacionesDto(
                                        verificacionId = 0,
                                        usuarioId = nuevoUsuario.data.usuarioId ?: 0,
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
                                                    it.copy(errorMessage = null, cargando = true, modal = false)
                                                }
                                                onVerificacion()
                                            }
                                            is Resource.Error -> {
                                                _uiState.update {
                                                    it.copy(errorMessage = responseEmail.message, usuarios = emptyList(), cargando = false, tipoError = "T2", modal = true)
                                                }
                                            }
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    _uiState.update {
                                        it.copy(errorMessage = it.language!!.error_envio_codigo_verificacion, usuarios = emptyList(), cargando = false, tipoError = "T2", modal = true)
                                    }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = it.language!!.error_acceso_ajustes, usuarios = emptyList(), cargando = false, tipoError = "T2", modal = true)
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


    fun onEmailChange(email: String?) {
        _uiState.update { it.copy(email = email.toString()) }
    }

    fun onContrasenaChange(contrasena: String?) {
        _uiState.update { it.copy(contrasena = contrasena.toString()) }
    }

    fun onOlvideContrasenaClick(onNavOlvidasteContrasenaClick: () ->  Unit) = viewModelScope.launch {
        onNavOlvidasteContrasenaClick()
    }

    fun onCerrarModalClick(){
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
    }

    fun onIniciarSesionClick(onAutenticado: () -> Unit) = viewModelScope.launch {
        iniciarSesion(onAutenticado)
    }

    fun onVerificarCuentaClick(onVerificacion: () -> Unit){
        verificarCuenta(onVerificacion)
    }
}



data class UiState(
    val usuarioId: Int = 0,
    val usuarios: List<UsuariosDto> = emptyList(),
    val email: String? = null,
    val contrasena: String? = null,
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