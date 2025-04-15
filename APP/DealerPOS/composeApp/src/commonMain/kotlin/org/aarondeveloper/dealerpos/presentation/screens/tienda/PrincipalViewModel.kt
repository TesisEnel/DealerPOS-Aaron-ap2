package org.aarondeveloper.dealerpos.presentation.screens.tienda

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
|   T3 - Producto no identificado    |
|   T4 - Problemas favoritos         |
|   T5 - Error adicionales           |
|   T6 - Agregado su carrito         |
|   T7 - Otros errores               |
|                                    |
------------------------------------*/

class PrincipalViewModel(
    private val configuracionesRepository: ConfiguracionRepository,
    private val auteticacionesRepository: AuteticacionesRepository,
    private val usuariosRepository: UsuariosRepository,
    private val categoriasRepository: CategoriasRepository,
    private val productosRepository: ProductosRepository,
    private val detalleProductoSucursalesRepository: DetalleProductoSucursalesRepository,
    private val lenguajeRepository: LenguajesRepository,
    private val caracteristicasRepository: CaracteristicasRepository,
    private val adicionalesRepository: AdicionalesRepository,
    private val iconosRepository: IconosRepository,
    private val favoritosRepository: FavoritosRepository,
    private val carritoRepository: CarritoRepository,
    private val detalleCarritoAdicionalRepository: DetalleCarritoAdicionalRepository

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
                            it.copy(usuarioId = resource.data!!.usuarioId, nombreUsuario = resource.data.nombre + " " + resource.data.apellido, imagen = resource.data.imagen, sexo = resource.data.sexo, cargando = true, errorMessage = null)
                        }
                        getCategorias()
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

    fun cerrarSesion(onNoAutenticado: () -> Unit) {

        viewModelScope.launch {

            configuracionesRepository.getAutenticacionId().collect { responseConfiguracion ->
                when (responseConfiguracion) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(errorMessage = null, cargando = false, modal = false)
                        }

                        auteticacionesRepository.updateEstado(responseConfiguracion.data ?: 0, "Cerrado").collect { responseAutenticacion ->
                            when (responseAutenticacion) {
                                is Resource.Loading -> {
                                    _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                }
                                is Resource.Success -> {
                                    _uiState.update {
                                        it.copy(errorMessage = null, cargando = false, modal = false)
                                    }
                                    onNoAutenticado()
                                }
                                is Resource.Error -> {
                                    _uiState.update { it.copy(errorMessage = it.language!!.identificacion_falta, tipoError = "T1", modal = true, cargando = false) }
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = responseConfiguracion.message, tipoError = "T2", modal = true, cargando = false) }
                    }
                }
            }
        }
    }

    fun getCategorias(){
        viewModelScope.launch {
            categoriasRepository.getCategorias().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(cargando = true, errorMessage = null)
                        }
                    }
                    is Resource.Success -> {
                        val categoriaTodos = CategoriasDto(
                            categoriaId = 0,
                            descripcion = "Todos",
                            imagen = CATEGORIATODOS
                        )

                        val categoriasConTodos = resource.data?.toMutableList() ?: mutableListOf()
                        categoriasConTodos.add(0, categoriaTodos)
                        _uiState.update { it.copy(categorias = categoriasConTodos, listaCategorias = categoriasConTodos, cargando = true, errorMessage = null) }

                        getProductos()
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(errorMessage = resource.message, tipoError = "T2", modal = true, cargando = false)
                        }
                    }
                }
            }
        }
    }

    fun getProductos() {
        viewModelScope.launch {

            configuracionesRepository.getSucursalId().collect { responseConfiguracion ->
                when (responseConfiguracion) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                    }

                    is Resource.Success -> {
                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }

                        if(responseConfiguracion.data == 0){
                            _uiState.update {
                                it.copy(cargando = false, errorMessage = null, estadoBusqueda = it.language!!.seleccione_sucursal)
                            }
                        }
                        else{

                            detalleProductoSucursalesRepository.getDetalleProductoSucursales().collect { responseDetalleProductoSucursales ->
                                when (responseDetalleProductoSucursales) {
                                    is Resource.Loading -> {
                                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                    }

                                    is Resource.Success -> {
                                        _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }

                                        val productosEnSucursal = responseDetalleProductoSucursales.data!!.filter { detalle ->
                                            detalle.sucursalId == responseConfiguracion.data
                                        }

                                        if (productosEnSucursal.isNotEmpty()) {
                                            val productosObtenidos = mutableListOf<ProductosDto>()

                                            for (producto in productosEnSucursal) {
                                                productosRepository.getProductoById(producto.productoId).collect { resource ->
                                                    when (resource) {
                                                        is Resource.Loading -> {
                                                            _uiState.update {
                                                                it.copy(cargando = true, errorMessage = null, estadoBusqueda = it.language!!.buscando_articulos)
                                                            }
                                                        }

                                                        is Resource.Success -> {
                                                            if (resource.data != null) {
                                                                productosObtenidos.add(resource.data)
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

                                            if (productosObtenidos.isEmpty()) {
                                                _uiState.update {
                                                    it.copy(cargando = false, errorMessage = null, estadoBusqueda = it.language!!.tienda_sin_articulos)
                                                }
                                            } else {
                                                _uiState.update { it.copy(productos = productosObtenidos, listaProductos = productosObtenidos, cargando = false, errorMessage = null) }
                                            }
                                        } else {
                                            _uiState.update {
                                                it.copy(cargando = false, estadoBusqueda = it.language!!.sucursal_sin_producos)
                                            }
                                        }
                                    }

                                    is Resource.Error -> {
                                        _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, tipoError = "T2", modal = true, cargando = false) }
                                    }
                                }
                            }

                        }
                    }

                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_configuracion, tipoError = "T2", modal = true, cargando = false) }
                    }
                }
            }
        }
    }

    fun getDetalleProducto() {

        viewModelScope.launch {

            configuracionesRepository.getProductoId().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(cargando = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { it.copy(cargando = false) }

                        configuracionesRepository.getUsuarioId().collect { responseConfiguracionUsuario ->
                            when (responseConfiguracionUsuario) {
                                is Resource.Loading -> {
                                    _uiState.update { it.copy(errorMessage = null, cargando = true, modal = false) }
                                }

                                is Resource.Success -> {
                                    _uiState.update { it.copy(usuarioId = responseConfiguracionUsuario.data,errorMessage = null, cargando = true, modal = false) }

                                    val productoId = result.data ?: 0

                                    if(productoId != 0){

                                        productosRepository.getProductoById(productoId).collect { resultProducto ->
                                            when (resultProducto) {
                                                is Resource.Loading -> {
                                                    _uiState.update { it.copy(cargando = true) }
                                                }

                                                is Resource.Success -> {
                                                    _uiState.update { it.copy(cargando = false) }

                                                    val producto = resultProducto.data

                                                    if (producto != null) {
                                                        _uiState.update { it.copy(productoId = producto.productoId ,nombreProducto = producto.nombre, descripcionProducto = producto.descripcion, precioProducto = producto.precio, imagenProducto = producto.imagen, cantidadProducto =  producto.cantidad, cargando = true) }

                                                        caracteristicasRepository.getCaracteristicas().collect { resultCaracteristicas ->
                                                            when (resultCaracteristicas) {
                                                                is Resource.Loading -> {
                                                                    _uiState.update {
                                                                        it.copy(cargando = true, errorMessage = null)
                                                                    }
                                                                }
                                                                is Resource.Success -> {
                                                                    val caracteristicasProducto = resultCaracteristicas.data?.filter { it.productoId == productoId }

                                                                    val iconosEncontrados = mutableListOf<IconosDto>()
                                                                    caracteristicasProducto?.forEach { caracteristica ->
                                                                        iconosRepository.getIconoById(caracteristica.iconoId).collect { resultIcono ->
                                                                            if (resultIcono is Resource.Success) {
                                                                                resultIcono.data?.let { iconosEncontrados.add(it) }
                                                                            }
                                                                        }
                                                                    }

                                                                    _uiState.update {
                                                                        it.copy(caracteristicas = caracteristicasProducto, iconos = iconosEncontrados, cargando = true)
                                                                    }


                                                                    adicionalesRepository.getAdicionales().collect { resultAdicionales ->
                                                                        when (resultAdicionales) {
                                                                            is Resource.Loading -> {
                                                                                _uiState.update {
                                                                                    it.copy(cargando = true, errorMessage = null)
                                                                                }
                                                                            }
                                                                            is Resource.Success -> {
                                                                                val adicionalesProducto = resultAdicionales.data?.filter { it.productoId == productoId }
                                                                                _uiState.update { it.copy(adicionales = adicionalesProducto, cargando = false) }

                                                                                onCalcularTotal()
                                                                                onFavoritoChange()

                                                                            }
                                                                            is Resource.Error -> {
                                                                                _uiState.update { it.copy(errorMessage = it.language?.error_conexion, cargando = false, modal = true, tipoError = "T1") }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                is Resource.Error -> {
                                                                    _uiState.update { it.copy(errorMessage = it.language?.error_conexion, cargando = false, modal = true, tipoError = "T1") }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        _uiState.update { it.copy(errorMessage = "Producto no encontrado", cargando = false, modal = true, tipoError = "T3") }
                                                    }

                                                }

                                                is Resource.Error -> {
                                                    _uiState.update { it.copy(errorMessage = "Producto no encontrado", cargando = false, modal = true, tipoError = "T3") }
                                                }
                                            }
                                        }

                                    }
                                    else{
                                        _uiState.update { it.copy(errorMessage = "Producto no encontrado", cargando = false, modal = true, tipoError = "T3") }
                                    }
                                }

                                is Resource.Error -> {
                                    _uiState.update { it.copy(errorMessage = it.language!!.error_configuracion, tipoError = "T2", modal = true, cargando = false) }
                                }
                            }
                        }

                    }

                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T1") }
                    }
                }
            }

        }
    }

    fun verificarFavorito(usuarioId: Int, productoId: Int) {

        viewModelScope.launch {
            favoritosRepository.getFavoritos().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(cargando = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(cargando = false) }

                        val productoEnFavoritos = result.data?.firstOrNull { it.productoId == productoId && it.usuarioId == usuarioId }
                        if (productoEnFavoritos != null) {
                            _uiState.update { it.copy(favorito = productoEnFavoritos, isFavoritos = true) }
                        } else {
                            _uiState.update { it.copy(favorito = null, isFavoritos = false) }
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T1") }
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

    fun onSeleccionarCategoria(categoriaId: Int) {
        _uiState.update { it.copy(categoriaId = categoriaId) }
        if (categoriaId != 0) {
            val productosFiltrados = _uiState.value.listaProductos?.filter { producto ->
                producto.categoriaId == categoriaId
            }

            _uiState.update { it.copy(productos = productosFiltrados) }
        }
        else {
            _uiState.update { it.copy(productos = _uiState.value.listaProductos) }
        }
    }


    fun onSeleccionarProducto(productoId: Int, onDetalleProducto: () -> Unit){

        viewModelScope.launch {
            _uiState.update { it.copy(productoId = productoId) }

            if(productoId != 0){

                    configuracionesRepository.updateProductoId(productoId).collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _uiState.update { it.copy(cargando = true) }
                            }

                            is Resource.Success -> {
                                _uiState.update { it.copy(cargando = false) }
                                onDetalleProducto()
                            }

                            is Resource.Error -> {
                                _uiState.update { it.copy(errorMessage = result.message, cargando = false, modal = true, tipoError = "T1") }
                            }
                        }
                    }
            }
            else{
                _uiState.update { it.copy(modal = true, tipoError = "T3", errorMessage = "Hemos detectado un pequeño problema con este producto. \n\nIntentelo mas tarde!", cargando = false) }
            }
        }
    }

    fun onFavoritoChange(){
        verificarFavorito(_uiState.value.usuarioId ?: 0, _uiState.value.productoId ?: 0)
    }

    fun onAgregarCarritoChange() {
        viewModelScope.launch {
            if ((_uiState.value.cantidadProducto ?: 0) > 0) {

                if (_uiState.value.usuarioId != 0 && _uiState.value.productoId != 0) {

                    val carritoNuevo = CarritoEntity(
                        carritoId = 0,
                        usuarioId = _uiState.value.usuarioId!!,
                        productoId = _uiState.value.productoId!!,
                        cantidad = _uiState.value.cantidadSeleccionada ?: 1
                    )

                    carritoRepository.saveCarrito(carritoNuevo).collect { result ->

                        when (result) {
                            is Resource.Loading -> {
                                _uiState.update { it.copy(cargando = true) }
                            }

                            is Resource.Success -> {
                                logDebug("Carrito: " + result.data)
                                val carritoId = result.data?.carritoId ?: 0


                                if (carritoId != 0) {
                                    val adicionalesSeleccionados = _uiState.value.adicionalesSeleccionada
                                    if (adicionalesSeleccionados.isNotEmpty()) {
                                        val deferreds = adicionalesSeleccionados.map { adicional ->
                                            async {
                                                val detalleCarritoAdicional = DetalleCarritoAdicionalEntity(
                                                    detalleCarritoAdicionalId = 0,
                                                    carritoId = carritoId,
                                                    productoId = _uiState.value.productoId!!,
                                                    adicionalId = adicional.adicionalId,
                                                    cantidad = 1
                                                )

                                                detalleCarritoAdicionalRepository.saveDetalleCarritoAdicional(
                                                    detalleCarritoAdicional
                                                ).collect { detalleResult ->
                                                    when (detalleResult) {
                                                        is Resource.Loading -> {
                                                            _uiState.update {
                                                                it.copy(cargando = true)
                                                            }
                                                        }

                                                        is Resource.Success -> {
                                                            _uiState.update {
                                                                it.copy(cargando = false)
                                                            }
                                                        }

                                                        is Resource.Error -> {
                                                            _uiState.update {
                                                                it.copy(
                                                                    cargando = false,
                                                                    modal = true,
                                                                    errorMessage = "Error al agregar algunos adicionales. Intente más tarde.",
                                                                    tipoError = "T7"
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        try {
                                            deferreds.awaitAll()
                                            _uiState.update {
                                                it.copy(cargando = false, modal = true, errorMessage = "Agregado correctamente al carrito", tipoError = "T6")
                                            }
                                        } catch (e: Exception) {
                                            _uiState.update {
                                                it.copy(
                                                    errorMessage = "Error al guardar los adicionales.",
                                                    cargando = false,
                                                    modal = true,
                                                    tipoError = "T7"
                                                )
                                            }
                                        }
                                    } else {
                                        _uiState.update {
                                            it.copy(cargando = false, modal = true, errorMessage = "Agregado correctamente al carrito", tipoError = "T6")
                                        }
                                    }
                                } else {
                                    _uiState.update {
                                        it.copy(
                                            modal = true,
                                            tipoError = "T7",
                                            errorMessage = "Hubo un problema al agregar el producto. \n\nIntente más tarde.",
                                            cargando = false
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {
                                _uiState.update {
                                    it.copy(
                                        modal = true,
                                        tipoError = "T7",
                                        errorMessage = "Hubo un problema al agregar el producto. Intente más tarde.",
                                        cargando = false
                                    )
                                }
                            }
                        }
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            errorMessage = "Este producto está agotado y no puedes agregarlo al carrito de compra",
                            cargando = false
                        )
                    }
                }
            } else {
                _uiState.update {
                    it.copy(
                        errorMessage = "Este producto está agotado y no puedes agregarlo al carrito de compra",
                        cargando = false
                    )
                }
            }
        }
    }


    fun onCambiarFavoritoChange() {
        viewModelScope.launch {
            val productoEnFavoritos = _uiState.value.favorito

            if (productoEnFavoritos != null) {
                favoritosRepository.deleteFavorito(productoEnFavoritos.favoritoId).collect { resultFavoritoEliminar ->
                    when (resultFavoritoEliminar) {
                        is Resource.Loading -> {
                            _uiState.update { it.copy(cargando = true) }
                        }
                        is Resource.Success -> {
                            _uiState.update { it.copy(cargando = false, isFavoritos = false, favorito = null) }
                        }
                        is Resource.Error -> {
                            _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T2") }
                        }
                    }
                }
            } else {
                if (_uiState.value.productoId != 0 && _uiState.value.usuarioId != 0) {
                    val favorito = FavoritosDto(
                        favoritoId = 0,
                        productoId = _uiState.value.productoId!!,
                        usuarioId = _uiState.value.usuarioId!!
                    )

                    favoritosRepository.addFavorito(favorito).collect { resultFavoritoAgregar ->
                        when (resultFavoritoAgregar) {
                            is Resource.Loading -> {
                                _uiState.update { it.copy(cargando = true) }
                            }
                            is Resource.Success -> {
                                _uiState.update { it.copy(cargando = false, isFavoritos = true, favorito = resultFavoritoAgregar.data) }
                            }
                            is Resource.Error -> {
                                _uiState.update { it.copy(errorMessage = it.language!!.error_conexion, cargando = false, modal = true, tipoError = "T1") }
                            }
                        }
                    }
                } else {
                    _uiState.update { it.copy(errorMessage = "Hemos notado un imprevisto para agregar este producto en su lista de favoritos. \n\nIntentelo más tarde!", cargando = false, modal = true, tipoError = "T4") }
                }
            }
        }
    }

    fun onBusquedaChange(busqueda: String) {
        _uiState.update { it.copy(busqueda = busqueda) }
        if (busqueda.isNotEmpty()) {
            val productosFiltrados = _uiState.value.listaProductos?.filter { producto ->
                producto.nombre.contains(busqueda, ignoreCase = true)
            }
            _uiState.update { it.copy(productos = productosFiltrados) }
        } else {
            _uiState.update { it.copy(productos = _uiState.value.listaProductos) }
        }
    }


    fun onCerrarModalClick(){
        _uiState.update { it.copy(modal = false, tipoError = "", errorMessage = "", cargando = false) }
    }

    fun onCerrarSesionClick(onNoAutenticado: () -> Unit){
        cerrarSesion(onNoAutenticado)
    }

    fun onSeleccionarAdicionales(adicional: AdicionalesDto, isChecked: Boolean) {
        _uiState.update {
            val listaActual = it.adicionalesSeleccionada
            val listaActualizada = if (isChecked) {
                listaActual + adicional
            } else {
                listaActual.filter { it != adicional }
            }
            it.copy(adicionalesSeleccionada = listaActualizada)
        }
        onCalcularTotal()
    }

    fun onCantidadSeleccionadaChange(cantidad: Int): Int {
        val cantidadActual = _uiState.value.cantidadSeleccionada ?: 0
        val nuevaCantidad = cantidadActual + cantidad
        if (nuevaCantidad < 0) return cantidadActual

        _uiState.update { it.copy(cantidadSeleccionada = nuevaCantidad) }
        onCalcularTotal()

        return nuevaCantidad
    }

    fun onCalcularTotal() {
        val cantidadSeleccionada = _uiState.value.cantidadSeleccionada ?: 0
        val precioProducto = _uiState.value.precioProducto ?: 0f
        val adicionalesSeleccionados = _uiState.value.adicionalesSeleccionada

        val totalProducto = precioProducto * cantidadSeleccionada
        var totalAdicionales = 0f
        for (adicional in adicionalesSeleccionados) {
            totalAdicionales += adicional.precio
        }

        val totalFinal = totalProducto + totalAdicionales
        _uiState.update { it.copy(total = totalFinal) }
    }



}


data class UiState(
    val usuarioId: Int? = 0,
    val categoriaId: Int? = 0,
    val productoId: Int? = 0,
    val errorMessage: String? = null,
    val tipoError: String? = null,
    val modal: Boolean = false,
    val estadoBusqueda: String = "...",
    val cargando: Boolean = false,
    val busqueda: String? = "",
    val nombreUsuario: String? = "",
    val imagen: String? = "",
    val sexo: String? = "",
    val language: Language? = null,
    val listaProductos: List<ProductosDto>? = emptyList(),
    val listaCategorias: List<CategoriasDto>? = emptyList(),
    val productos: List<ProductosDto>? = emptyList(),
    val categorias: List<CategoriasDto>? = emptyList(),

    val nombreProducto: String? = ".....",
    val descripcionProducto: String? = ".....",
    val imagenProducto: String? = "",
    val precioProducto: Float? = 0f,
    val cantidadProducto: Int? = 0,
    val cantidadSeleccionada: Int? = 1,
    val isFavoritos: Boolean? = false,
    val favorito: FavoritosDto? = null,
    val adicionales: List<AdicionalesDto>? = emptyList(),
    val caracteristicas: List<CaracteristicasDto>? = emptyList(),
    val adicionalesSeleccionada: List<AdicionalesDto> = emptyList(),
    val iconos: List<IconosDto>? = emptyList(),
    val total: Float? = 0f,
)