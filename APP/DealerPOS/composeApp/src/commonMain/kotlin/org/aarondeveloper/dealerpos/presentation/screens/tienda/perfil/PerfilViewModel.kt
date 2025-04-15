package org.aarondeveloper.dealerpos.presentation.screens.tienda.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.UsuariosDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.CiudadesDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.EstadosDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.PaisesDto
import org.aarondeveloper.dealerpos.data.repository.AuteticacionesRepository
import org.aarondeveloper.dealerpos.data.repository.CiudadesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.EstadosRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.PaisesRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.obtenerCodigo
import org.aarondeveloper.dealerpos.ui.languages.Language


/*----------TIPOS DE ERRORES---------|
|                                    |
|   T1 - Usuario no identificado     |
|   T2 - Conexion                    |
|   T3 - No guardado                 |
|   T4 - Campos Vacios               |
|                                    |
------------------------------------*/

class PerfilViewModel(
    private val configuracionRepository: ConfiguracionRepository,
    private val auteticacionesRepository: AuteticacionesRepository,
    private val usuariosRepository: UsuariosRepository,
    private val lenguajeRepository: LenguajesRepository,
    private val paisesRepository: PaisesRepository,
    private val estadosRepository: EstadosRepository,
    private val ciudadesRepository: CiudadesRepository,
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
                _uiState.value.paisId != 0 &&
                _uiState.value.estadoId != 0 &&
                _uiState.value.ciudadId != 0 &&
                !_uiState.value.telefono.isNullOrBlank() &&
                !_uiState.value.direccion.isNullOrBlank() &&
                !_uiState.value.sexo.isNullOrBlank()

    }

    fun verificarAuteticacion(onNoAutenticado: () -> Unit){

        viewModelScope.launch {

            configuracionRepository.getUsuarioId().collect { responseConfiguracion ->
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
                                    _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2") }
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
                        if(resource.data!!.rolId == 1){
                            _uiState.update { it.copy(rol = it.language!!.rol_administrador) }
                        }
                        else if(resource.data.rolId == 2){
                            _uiState.update { it.copy(rol = it.language!!.rol_cliente) }
                        }
                        else{
                            _uiState.update { it.copy(rol = it.language!!.desconocido) }
                        }
                        _uiState.update {
                            it.copy(
                                usuarioId = resource.data.usuarioId ?: 0,
                                imagen = resource.data.imagen ?: "",
                                nombre = resource.data.nombre ?: "",
                                apellido = resource.data.apellido ?: "",
                                telefono = resource.data.telefono ?: "",
                                sexo = resource.data.sexo ?: "",
                                email = resource.data.email ?: "",
                                paisId = resource.data.paisId ?: 0,
                                estadoId = resource.data.estadoId ?: 0,
                                ciudadId = resource.data.ciudadId ?: 0,
                                direccion = resource.data.direccion ?: "",
                                cargando = false,
                                errorMessage = null)
                        }
                        getPaises()
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
                        getEstados(_uiState.value.paisId)
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(paises = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T2", modal = true, cargando = false)
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
                        getCiudades(_uiState.value.estadoId)
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(estados = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T2", modal = true, cargando = false)
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
                            it.copy(ciudades = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T2", modal = true, cargando = false)
                        }
                    }
                }
            }
        }
    }

    fun guardarUsuario(){

        viewModelScope.launch {

            if (!validarCampos()) {
                _uiState.update { it.copy(errorMessage = _uiState.value.language!!.error_campos_vacios, tipoError = "T4", modal = true) }
                return@launch
            }
            else{
                usuariosRepository.getUsuarioById(_uiState.value.usuarioId).collect { resourceUsuario ->
                    when (resourceUsuario) {
                        is Resource.Loading -> {
                            _uiState.update {
                                it.copy(cargando = true, errorMessage = null)
                            }
                        }
                        is Resource.Success -> {

                            val usuario = UsuariosDto(
                                usuarioId = resourceUsuario.data!!.usuarioId,
                                rolId = resourceUsuario.data.rolId,
                                paisId = _uiState.value.paisId,
                                estadoId = _uiState.value.estadoId,
                                ciudadId = _uiState.value.ciudadId,
                                nombre = _uiState.value.nombre,
                                apellido = _uiState.value.apellido,
                                contrasena = resourceUsuario.data.contrasena,
                                sexo = _uiState.value.sexo,
                                telefono = _uiState.value.telefono,
                                email = _uiState.value.email,
                                direccion = _uiState.value.direccion,
                                imagen = _uiState.value.imagen,
                                verificado = resourceUsuario.data.verificado
                            )

                            usuariosRepository.updateUsuario(usuario).collect { resourceUsuarioUpdate ->
                                when (resourceUsuarioUpdate) {
                                    is Resource.Loading -> {
                                        _uiState.update {
                                            it.copy(cargando = true, errorMessage = null)
                                        }
                                    }
                                    is Resource.Success -> {

                                        _uiState.update {
                                            it.copy(errorMessage = it.language!!.guardado_exitoso, tipoError = "T3", modal = true, cargando = false)
                                        }

                                    }
                                    is Resource.Error -> {
                                        _uiState.update {
                                            it.copy(errorMessage = it.language!!.error_conexion, tipoError = "T2", modal = true, cargando = false)
                                        }
                                    }
                                }
                            }

                        }
                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(ciudades = emptyList(), errorMessage = it.language!!.error_conexion, tipoError = "T2", modal = true, cargando = false)
                            }
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

    fun onSexoChange(sexo: String?) {
        _uiState.update { it.copy(sexo = sexo.toString()) }
    }

    fun onTelefonoChange(telefono: String?) {
        _uiState.update { it.copy(telefono = telefono.toString()) }
    }

    fun onDireccionChange(direccion: String?) {
        _uiState.update { it.copy(direccion = direccion.toString()) }
    }

    fun onImagenChange(imagen: String) {
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

    fun onGuardarClick() {
        guardarUsuario()
    }

    fun onCerrarModalClick(){
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
    }

}

data class UiState(
    val usuarioId: Int = 0,
    val rol: String = "",
    val imagen: String = "",
    val nombre: String = "",
    val apellido: String = "",
    val sexo: String = "",
    val telefono: String = "",
    val email: String = "",
    val paisId: Int = 0,
    val estadoId: Int = 0,
    val ciudadId: Int = 0,
    val direccion: String = "",
    val cargando: Boolean = false,
    val errorMessage: String? = null,
    val tipoError: String? = null,
    val modal: Boolean = false,
    val language: Language? = null,
    val paises: List<PaisesDto> = emptyList(),
    val estados: List<EstadosDto> = emptyList(),
    val ciudades: List<CiudadesDto> = emptyList(),
)