package org.aarondeveloper.dealerpos.data.remote.remotedatasource

import org.aarondeveloper.dealerpos.data.remote.api.DealerPosApi
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AdicionalesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AjustesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AutenticacionesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CaracteristicasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CategoriasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.DetalleProductoSucursalesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.DetalleVentaPagosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.DetalleVentasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.FavoritosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.IconosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.ProductosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.ReclamacionesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.RolesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.SucursalesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.TarifasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.UsuariosDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.VentasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.VerificacionesDto

class DealerDataSource(private val api: DealerPosApi) {

    // Productos
    suspend fun getProductos(): List<ProductosDto> = api.getProductos()
    suspend fun getProductoById(id: Int) = api.getProductoById(id)
    suspend fun addProducto(productoDto: ProductosDto) = api.addProducto(productoDto)
    suspend fun updateProducto(productoDto: ProductosDto) = api.updateProducto(productoDto)
    suspend fun deleteProducto(id: Int) = api.deleteProducto(id)

    // Adicionales
    suspend fun getAdicionales(): List<AdicionalesDto> = api.getAdicionales()
    suspend fun getAdicionalById(id: Int) = api.getAdicionalById(id)
    suspend fun addAdicional(adicionalDto: AdicionalesDto) = api.addAdicional(adicionalDto)
    suspend fun updateAdicional(adicionalDto: AdicionalesDto) = api.updateAdicional(adicionalDto)
    suspend fun deleteAdicional(id: Int) = api.deleteAdicional(id)

    // Ajustes
    suspend fun getAjustes(): List<AjustesDto> = api.getAjustes()
    suspend fun getAjusteById(id: Int) = api.getAjusteById(id)
    suspend fun addAjuste(ajusteDto: AjustesDto) = api.addAjuste(ajusteDto)
    suspend fun updateAjuste(ajusteDto: AjustesDto) = api.updateAjuste(ajusteDto)
    suspend fun deleteAjuste(id: Int) = api.deleteAjuste(id)

    // Autenticaciones
    suspend fun getAutenticaciones(): List<AutenticacionesDto> = api.getAutenticaciones()
    suspend fun getAutenticacionById(id: Int) = api.getAutenticacionById(id)
    suspend fun addAutenticacion(autenticacionDto: AutenticacionesDto) = api.addAutenticacion(autenticacionDto)
    suspend fun updateAutenticacion(autenticacionDto: AutenticacionesDto) = api.updateAutenticacion(autenticacionDto)
    suspend fun deleteAutenticacion(id: Int) = api.deleteAutenticacion(id)

    // Caracteristicas
    suspend fun getCaracteristicas(): List<CaracteristicasDto> = api.getCaracteristicas()
    suspend fun getCaracteristicaById(id: Int) = api.getCaracteristicaById(id)
    suspend fun addCaracteristica(caracteristicaDto: CaracteristicasDto) = api.addCaracteristica(caracteristicaDto)
    suspend fun updateCaracteristica(caracteristicaDto: CaracteristicasDto) = api.updateCaracteristica(caracteristicaDto)
    suspend fun deleteCaracteristica(id: Int) = api.deleteCaracteristica(id)

    // Categorias
    suspend fun getCategorias(): List<CategoriasDto> = api.getCategorias()
    suspend fun getCategoriaById(id: Int) = api.getCategoriaById(id)
    suspend fun addCategoria(categoriaDto: CategoriasDto) = api.addCategoria(categoriaDto)
    suspend fun updateCategoria(categoriaDto: CategoriasDto) = api.updateCategoria(categoriaDto)
    suspend fun deleteCategoria(id: Int) = api.deleteCategoria(id)


    // DetalleProductoSucursales
    suspend fun getDetalleProductoSucursales(): List<DetalleProductoSucursalesDto> = api.getDetalleProductoSucursales()
    suspend fun getDetalleProductoSucursalById(id: Int) = api.getDetalleProductoSucursalById(id)
    suspend fun addDetalleProductoSucursal(detalleProductoSucursalDto: DetalleProductoSucursalesDto) = api.addDetalleProductoSucursal(detalleProductoSucursalDto)
    suspend fun updateDetalleProductoSucursal(detalleProductoSucursalDto: DetalleProductoSucursalesDto) = api.updateDetalleProductoSucursal(detalleProductoSucursalDto)
    suspend fun deleteDetalleProductoSucursal(id: Int) = api.deleteDetalleProductoSucursal(id)

    // DetalleVentaPagos
    suspend fun getDetalleVentaPagos(): List<DetalleVentaPagosDto> = api.getDetalleVentaPagos()
    suspend fun getDetalleVentaPagoById(id: Int) = api.getDetalleVentaPagoById(id)
    suspend fun addDetalleVentaPago(detalleVentaPagoDto: DetalleVentaPagosDto) = api.addDetalleVentaPago(detalleVentaPagoDto)
    suspend fun updateDetalleVentaPago(detalleVentaPagoDto: DetalleVentaPagosDto) = api.updateDetalleVentaPago(detalleVentaPagoDto)
    suspend fun deleteDetalleVentaPago(id: Int) = api.deleteDetalleVentaPago(id)

    // DetalleVentas
    suspend fun getDetalleVentas(): List<DetalleVentasDto> = api.getDetalleVentas()
    suspend fun getDetalleVentaById(id: Int) = api.getDetalleVentaById(id)
    suspend fun addDetalleVenta(detalleVentaDto: DetalleVentasDto) = api.addDetalleVenta(detalleVentaDto)
    suspend fun updateDetalleVenta(detalleVentaDto: DetalleVentasDto) = api.updateDetalleVenta(detalleVentaDto)
    suspend fun deleteDetalleVenta(id: Int) = api.deleteDetalleVenta(id)

    // Ventas
    suspend fun getVentas(): List<VentasDto> = api.getVentas()
    suspend fun getVentaById(id: Int) = api.getVentaById(id)
    suspend fun addVenta(ventaDto: VentasDto) = api.addVenta(ventaDto)
    suspend fun updateVenta(ventaDto: VentasDto) = api.updateVenta(ventaDto)
    suspend fun deleteVenta(id: Int) = api.deleteVenta(id)

    // Verificaciones
    suspend fun getVerificaciones(): List<VerificacionesDto> = api.getVerificaciones()
    suspend fun getVerificacionById(id: Int) = api.getVerificacionById(id)
    suspend fun addVerificacion(verificacionDto: VerificacionesDto) = api.addVerificacion(verificacionDto)
    suspend fun updateVerificacion(verificacionDto: VerificacionesDto) = api.updateVerificacion(verificacionDto)
    suspend fun deleteVerificacion(id: Int) = api.deleteVerificacion(id)

    // Usuarios
    suspend fun getUsuarios(): List<UsuariosDto> = api.getUsuarios()
    suspend fun getUsuarioById(id: Int) = api.getUsuarioById(id)
    suspend fun addUsuario(usuarioDto: UsuariosDto) = api.addUsuario(usuarioDto)
    suspend fun updateUsuario(usuarioDto: UsuariosDto) = api.updateUsuario(usuarioDto)
    suspend fun deleteUsuario(id: Int) = api.deleteUsuario(id)

    // Favoritos
    suspend fun getFavoritos(): List<FavoritosDto> = api.getFavoritos()
    suspend fun getFavoritoById(id: Int) = api.getFavoritoById(id)
    suspend fun addFavorito(favoritoDto: FavoritosDto) = api.addFavorito(favoritoDto)
    suspend fun updateFavorito(favoritoDto: FavoritosDto) = api.updateFavorito(favoritoDto)
    suspend fun deleteFavorito(id: Int) = api.deleteFavorito(id)

    // Iconos
    suspend fun getIconos(): List<IconosDto> = api.getIconos()
    suspend fun getIconoById(id: Int) = api.getIconoById(id)
    suspend fun addIcono(iconoDto: IconosDto) = api.addIcono(iconoDto)
    suspend fun updateIcono(iconoDto: IconosDto) = api.updateIcono(iconoDto)
    suspend fun deleteIcono(id: Int) = api.deleteIcono(id)

    // Reclamaciones
    suspend fun getReclamaciones(): List<ReclamacionesDto> = api.getReclamaciones()
    suspend fun getReclamacionById(id: Int) = api.getReclamacionById(id)
    suspend fun addReclamacion(reclamacionDto: ReclamacionesDto) = api.addReclamacion(reclamacionDto)
    suspend fun updateReclamacion(reclamacionDto: ReclamacionesDto) = api.updateReclamacion(reclamacionDto)
    suspend fun deleteReclamacion(id: Int) = api.deleteReclamacion(id)

    // Roles
    suspend fun getRoles(): List<RolesDto> = api.getRoles()
    suspend fun getRolById(id: Int) = api.getRolById(id)
    suspend fun addRol(rolDto: RolesDto) = api.addRol(rolDto)
    suspend fun updateRol(rolDto: RolesDto) = api.updateRol(rolDto)
    suspend fun deleteRol(id: Int) = api.deleteRol(id)

    // Sucursales
    suspend fun getSucursales(): List<SucursalesDto> = api.getSucursales()
    suspend fun getSucursalById(id: Int) = api.getSucursalById(id)
    suspend fun addSucursal(sucursalDto: SucursalesDto) = api.addSucursal(sucursalDto)
    suspend fun updateSucursal(sucursalDto: SucursalesDto) = api.updateSucursal(sucursalDto)
    suspend fun deleteSucursal(id: Int) = api.deleteSucursal(id)

    // Tarifas
    suspend fun getTarifas(): List<TarifasDto> = api.getTarifas()
    suspend fun getTarifaById(id: Int) = api.getTarifaById(id)
    suspend fun addTarifa(tarifaDto: TarifasDto) = api.addTarifa(tarifaDto)
    suspend fun updateTarifa(tarifaDto: TarifasDto) = api.updateTarifa(tarifaDto)
    suspend fun deleteTarifa(id: Int) = api.deleteTarifa(id)

}
