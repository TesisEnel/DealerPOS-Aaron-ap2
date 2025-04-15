package org.aarondeveloper.dealerpos.presentation.screens.registrate

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
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.CiudadesDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.EstadosDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.PaisesDto
import org.aarondeveloper.dealerpos.data.repository.AjustesRepository
import org.aarondeveloper.dealerpos.data.repository.CiudadesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.EmailSendRepository
import org.aarondeveloper.dealerpos.data.repository.EstadosRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.PaisesRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.data.repository.VerificacionesRepository
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.getFechaVencimientoCodigo
import org.aarondeveloper.dealerpos.ui.languages.Language


/*----------TIPOS DE ERRORES---------|
|                                    |
|   T1 - Campos vacios               |
|   T2 - Contrasena no coincide      |
|   T3 - Usuario ya existe           |
|   T4 - Conexion                    |
|   T5 - Usuario no identificado     |
|                                    |
------------------------------------*/

class RegistrateViewModel(
    private val paisesRepository: PaisesRepository,
    private val estadosRepository: EstadosRepository,
    private val ciudadesRepository: CiudadesRepository,
    private val usuariosRepository: UsuariosRepository,
    private val emailSendRepository: EmailSendRepository,
    private val ajustesRepository: AjustesRepository,
    private val configuracionesRepository: ConfiguracionRepository,
    private val verificacionesRepository: VerificacionesRepository,
    private val lenguajeRepository: LenguajesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getLenguajes()
    }

    fun validarCampos(): Boolean {
        return !_uiState.value.nombre.isNullOrBlank() &&
                !_uiState.value.apellido.isNullOrBlank() &&
                !_uiState.value.email.isNullOrBlank() &&
                !_uiState.value.contrasena.isNullOrBlank() &&
                !_uiState.value.repetirContrasena.isNullOrBlank() &&
                _uiState.value.paisId != 0 &&
                _uiState.value.estadoId != 0 &&
                _uiState.value.ciudadId != 0 &&
                !_uiState.value.telefono.isNullOrBlank() &&
                !_uiState.value.direccion.isNullOrBlank() &&
                !_uiState.value.sexo.isNullOrBlank()

    }

    fun validarContrasena(): Boolean {
        return _uiState.value.contrasena == _uiState.value.repetirContrasena
    }


    fun save(onNavConfirmarClick: () ->  Unit) {
        viewModelScope.launch {
            usuariosRepository.getUsuarios().collect { responseGetUsuario ->
                when (responseGetUsuario) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true) }
                    }
                    is Resource.Success -> {
                        val usuarios = responseGetUsuario.data ?: emptyList()
                        _uiState.update { it.copy(usuarios = usuarios, cargando = true) }

                        if (!validarCampos()) {
                            _uiState.update {
                                it.copy(
                                    errorMessage = _uiState.value.language!!.error_campos_vacios,
                                    guardado = false,
                                    tipoError = "T1",
                                    modal = true
                                )
                            }
                            return@collect
                        }

                        val existe = usuarios.firstOrNull { it.email == _uiState.value.email }
                        if (existe != null) {
                            _uiState.update { it.copy(errorMessage = it.language!!.error_usuario_existente, guardado = false, tipoError = "T3", modal = true) }
                            return@collect
                        }

                        if (!validarContrasena()) {
                            _uiState.update { it.copy(errorMessage = it.language!!.error_contrasena_no_coinciden, guardado = false, tipoError = "T2", modal = true) }
                            return@collect
                        }

                        val nuevoUsuario = UsuariosDto(
                            usuarioId = 0,
                            rolId = 2,
                            paisId = _uiState.value.paisId ?: 0,
                            estadoId = _uiState.value.estadoId ?: 0,
                            ciudadId = _uiState.value.ciudadId ?: 0,
                            nombre = _uiState.value.nombre ?: "",
                            apellido = _uiState.value.apellido ?: "",
                            contrasena = _uiState.value.contrasena ?: "",
                            sexo = _uiState.value.sexo ?: "",
                            telefono = _uiState.value.telefono ?: "",
                            email = _uiState.value.email ?: "",
                            direccion = _uiState.value.direccion ?: "",
                            imagen = _uiState.value.imagen ?: "",
                            verificado = "No"
                        )

                        usuariosRepository.addUsuario(nuevoUsuario).collect { responseNuevoUsuario ->
                            when (responseNuevoUsuario) {
                                is Resource.Loading -> {
                                    _uiState.update {
                                        it.copy(errorMessage = null, guardado = false, cargando = true, modal = false)
                                    }
                                }
                                is Resource.Success -> {
                                    val usuarioGuardado = responseNuevoUsuario.data
                                    _uiState.update {
                                        it.copy(errorMessage = null, guardado = true, cargando = false, modal = false)
                                    }

                                    configuracionesRepository.updateUsuarioId(usuarioGuardado!!.usuarioId ?: 0).collect { responseConfiguracion ->
                                        when (responseConfiguracion) {
                                            is Resource.Loading -> {
                                                _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                            }
                                            is Resource.Success -> {
                                                _uiState.update {
                                                    it.copy(errorMessage = null, cargando = false, guardado = true, modal = false)
                                                }
                                                sendEmail(_uiState.value.email ?: "", usuarioGuardado, onNavConfirmarClick)
                                            }
                                            is Resource.Error -> {
                                                _uiState.update {
                                                    it.copy(errorMessage = it.language!!.error_configuracion, cargando = false, modal = true, tipoError = "T4")
                                                }
                                            }
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, guardado = false, cargando = false, tipoError = "T4", modal = true) }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, usuarios = emptyList(), cargando = false, tipoError = "T4", modal = true) }
                    }
                }
            }
        }
    }


    private fun sendEmail(email: String, nuevoUsuario: UsuariosDto, onNavConfirmarClick: () -> Unit) {
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
                            ${_uiState.value.language?.email_part1} ${nuevoUsuario.nombre ?: ""} ${nuevoUsuario.apellido ?: ""},
                            
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
                                        it.copy(errorMessage = null, cargando = false, guardado = true, modal = false)
                                    }

                                    val fechaVencimiento: String = getFechaVencimientoCodigo()

                                    val verificacion = VerificacionesDto(
                                        verificacionId = 0,
                                        usuarioId = nuevoUsuario.usuarioId ?: 0,
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
                                                    it.copy(errorMessage = null, cargando = true, guardado = true, modal = false)
                                                }
                                                onNavConfirmarClick()
                                            }
                                            is Resource.Error -> {
                                                _uiState.update {
                                                    it.copy(errorMessage = it.language!!.error_conexion, usuarios = emptyList(), cargando = false, tipoError = "T4", modal = true)
                                                }
                                            }
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    _uiState.update {
                                        it.copy(errorMessage = it.language!!.error_envio_codigo_verificacion, usuarios = emptyList(), cargando = false, tipoError = "T4", modal = true)
                                    }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = it.language!!.error_acceso_ajustes, usuarios = emptyList(), cargando = false, tipoError = "T4", modal = true)
                        }
                    }
                }
            }
        }
    }


    private fun getPaises() {
        viewModelScope.launch {
            paisesRepository.getPaises().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(cargando = true, errorMessage = null)
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(paises = resource.data ?: emptyList(), cargando = false, errorMessage = null)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(paises = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T4", modal = true, cargando = false)
                        }
                    }
                }
            }
        }
    }

    fun getEstados(paisId: Int) {
        viewModelScope.launch {
            estadosRepository.getEstadosByPais(paisId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(cargando = true, errorMessage = null) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(estados = resource.data ?: emptyList(), cargando = false, errorMessage = null)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(estados = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T4", modal = true, cargando = false)
                        }
                    }
                }
            }
        }
    }

    fun getCiudades(estadoId: Int) {
        viewModelScope.launch {
            ciudadesRepository.getCiudadesByEstado(estadoId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(cargando = true, errorMessage = null)
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(ciudades = resource.data ?: emptyList(), cargando = false, errorMessage = null)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(ciudades = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T4", modal = true, cargando = false)
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

    fun onNombreChange(nombre: String?) {
        _uiState.update { it.copy(nombre = nombre.toString()) }
    }

    fun onApellidoChange(apellido: String?) {
        _uiState.update { it.copy(apellido = apellido.toString()) }
    }

    fun onEmailChange(email: String?) {
        _uiState.update { it.copy(email = email.toString()) }
    }

    fun onSexoChange(sexo: String?) {
        _uiState.update { it.copy(sexo = sexo.toString()) }
    }

    fun onContrasenaChange(contrasena: String?) {
        _uiState.update { it.copy(contrasena = contrasena.toString()) }
    }

    fun onRepetirContrasenaChange(repetirContrasena: String?) {
        _uiState.update { it.copy(repetirContrasena = repetirContrasena.toString()) }
    }

    fun onTelefonoChange(telefono: String?) {
        _uiState.update { it.copy(telefono = telefono.toString()) }
    }

    fun onDireccionChange(direccion: String?) {
        _uiState.update { it.copy(direccion = direccion.toString()) }
    }

    fun onImagenChange(imagen: String?) {
        _uiState.update { it.copy(imagen = imagen) }
    }

    fun onPaisChange(paisId: Int) {
        _uiState.update { it.copy(paisId = paisId) }
        getPaises()
        getEstados(paisId)
    }

    fun onEstadoChange(estadoId: Int) {
        _uiState.update { it.copy(estadoId = estadoId) }
        getCiudades(estadoId)
    }

    fun onCiudadChange(ciudadId: Int) {
        _uiState.update { it.copy(ciudadId = ciudadId) }
    }

    fun onCerrarModalClick(){
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
    }


    fun onConfirmarClick(onNavConfirmarClick: () -> Unit) = viewModelScope.launch {
        _uiState.update { it.copy(tipoError = "", modal = false, errorMessage = "", cargando = true) }
        save(onNavConfirmarClick)
    }
}



data class UiState(
    val usuarioId: Int? = 0,
    val nombre: String? = "",
    val apellido: String? = "",
    val email: String? = "",
    val contrasena: String? = "",
    val repetirContrasena: String? = "",
    val sexo: String? = "",
    val telefono: String? = "",
    val direccion: String? = "",
    val imagen: String? = null,
    val rolId: Int? = 0,
    val paisId: Int? = 0,
    val estadoId: Int? = 0,
    val ciudadId: Int? = 0,
    val varificado: String = "",
    val usuarios: List<UsuariosDto> = emptyList(),
    val paises: List<PaisesDto> = emptyList(),
    val estados: List<EstadosDto> = emptyList(),
    val ciudades: List<CiudadesDto> = emptyList(),
    val errorMessage: String? = null,
    val guardado: Boolean = false,
    val tipoError: String? = null,
    val modal: Boolean = false,
    val cargando: Boolean = false,
    val smtpHost: String? = "",
    val smtpUsername: String? = "",
    val smtpPassword: String? = "",
    val smtpPort: Int? = 0,
    val language: Language? = null
)


fun UiState.toEntity() = UsuariosDto(
    usuarioId = usuarioId ?: 0,
    rolId = rolId ?: 0,
    paisId = paisId ?: 0,
    estadoId = estadoId ?: 0,
    ciudadId = ciudadId ?: 0,
    nombre = nombre ?: "",
    apellido = apellido ?: "",
    contrasena = contrasena ?: "",
    sexo = sexo ?: "",
    telefono = telefono ?: "",
    email = email ?: "",
    direccion = direccion ?: "",
    imagen = imagen ?: "",
    verificado = varificado ?: ""
)
