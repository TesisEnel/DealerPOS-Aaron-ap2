@file:OptIn(InternalAPI::class)

package org.aarondeveloper.dealerpos.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.call.body
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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

class DealerPosApi(private val client: HttpClient, private val baseUrl: String) {

    // Productos
    suspend fun getProductos(): List<ProductosDto> {
        return client.get("$baseUrl/api/Productos").body()
    }
    suspend fun getProductoById(id: Int): ProductosDto {
        return client.get("$baseUrl/api/Productos/$id").body()
    }
    suspend fun addProducto(productosDto: ProductosDto): ProductosDto {
        return client.post("$baseUrl/api/Productos") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(productosDto)
        }.body()
    }
    suspend fun updateProducto(productosDto: ProductosDto): ProductosDto {
        return client.put("$baseUrl/api/Productos/${productosDto.productoId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(productosDto)
        }.body()
    }
    suspend fun deleteProducto(id: Int): ProductosDto {
        return client.delete("$baseUrl/api/Productos/$id").body()
    }

    // Adicionales
    suspend fun getAdicionales(): List<AdicionalesDto> {
        return client.get("$baseUrl/api/Adicionales").body()
    }
    suspend fun getAdicionalById(id: Int): AdicionalesDto {
        return client.get("$baseUrl/api/Adicionales/$id").body()
    }
    suspend fun addAdicional(adicionalesDto: AdicionalesDto): AdicionalesDto {
        return client.post("$baseUrl/api/Adicionales") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(adicionalesDto)
        }.body()
    }
    suspend fun updateAdicional(adicionalesDto: AdicionalesDto): AdicionalesDto {
        return client.put("$baseUrl/api/Adicionales/${adicionalesDto.adicionalId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(adicionalesDto)
        }.body()
    }
    suspend fun deleteAdicional(id: Int): AdicionalesDto {
        return client.delete("$baseUrl/api/Adicionales/$id").body()
    }

    // Ajustes
    suspend fun getAjustes(): List<AjustesDto> {
        return client.get("$baseUrl/api/Ajustes").body()
    }
    suspend fun getAjusteById(id: Int): AjustesDto {
        return client.get("$baseUrl/api/Ajustes/$id").body()
    }
    suspend fun addAjuste(ajustesDto: AjustesDto): AjustesDto {
        return client.post("$baseUrl/api/Ajustes") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(ajustesDto)
        }.body()
    }
    suspend fun updateAjuste(ajustesDto: AjustesDto): AjustesDto {
        return client.put("$baseUrl/api/Ajustes/${ajustesDto.ajusteId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(ajustesDto)
        }.body()
    }
    suspend fun deleteAjuste(id: Int): AjustesDto {
        return client.delete("$baseUrl/api/Ajustes/$id").body()
    }

    // Autenticaciones
    suspend fun getAutenticaciones(): List<AutenticacionesDto> {
        return client.get("$baseUrl/api/Autenticaciones").body()
    }
    suspend fun getAutenticacionById(id: Int): AutenticacionesDto {
        return client.get("$baseUrl/api/Autenticaciones/$id").body()
    }
    suspend fun addAutenticacion(autenticacionesDto: AutenticacionesDto): AutenticacionesDto {
        return client.post("$baseUrl/api/Autenticaciones") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(autenticacionesDto)
        }.body()
    }
    suspend fun updateAutenticacion(autenticacionesDto: AutenticacionesDto): AutenticacionesDto {
        return client.put("$baseUrl/api/Autenticaciones/${autenticacionesDto.autenticacionId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(autenticacionesDto)
        }.body()
    }
    suspend fun deleteAutenticacion(id: Int): AutenticacionesDto {
        return client.delete("$baseUrl/api/Autenticaciones/$id").body()
    }

    // Caracteristicas
    suspend fun getCaracteristicas(): List<CaracteristicasDto> {
        return client.get("$baseUrl/api/Caracteristicas").body()
    }
    suspend fun getCaracteristicaById(id: Int): CaracteristicasDto {
        return client.get("$baseUrl/api/Caracteristicas/$id").body()
    }
    suspend fun addCaracteristica(caracteristicasDto: CaracteristicasDto): CaracteristicasDto {
        return client.post("$baseUrl/api/Caracteristicas") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(caracteristicasDto)
        }.body()
    }
    suspend fun updateCaracteristica(caracteristicasDto: CaracteristicasDto): CaracteristicasDto {
        return client.put("$baseUrl/api/Caracteristicas/${caracteristicasDto.caracteristicaId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(caracteristicasDto)
        }.body()
    }
    suspend fun deleteCaracteristica(id: Int): CaracteristicasDto {
        return client.delete("$baseUrl/api/Caracteristicas/$id").body()
    }

    // Categorias
    suspend fun getCategorias(): List<CategoriasDto> {
        return client.get("$baseUrl/api/Categorias").body()
    }
    suspend fun getCategoriaById(id: Int): CategoriasDto {
        return client.get("$baseUrl/api/Categorias/$id").body()
    }
    suspend fun addCategoria(categoriasDto: CategoriasDto): CategoriasDto {
        return client.post("$baseUrl/api/Categorias") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(categoriasDto)
        }.body()
    }
    suspend fun updateCategoria(categoriasDto: CategoriasDto): CategoriasDto {
        return client.put("$baseUrl/api/Categorias/${categoriasDto.categoriaId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(categoriasDto)
        }.body()
    }
    suspend fun deleteCategoria(id: Int): CategoriasDto {
        return client.delete("$baseUrl/api/Categorias/$id").body()
    }

    // DetalleProductoSucursales
    suspend fun getDetalleProductoSucursales(): List<DetalleProductoSucursalesDto> {
        return client.get("$baseUrl/api/DetalleProductoSucursales").body()
    }
    suspend fun getDetalleProductoSucursalById(id: Int): DetalleProductoSucursalesDto {
        return client.get("$baseUrl/api/DetalleProductoSucursales/$id").body()
    }
    suspend fun addDetalleProductoSucursal(detalleProductoSucursalesDto: DetalleProductoSucursalesDto): DetalleProductoSucursalesDto {
        return client.post("$baseUrl/api/DetalleProductoSucursales") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(detalleProductoSucursalesDto)
        }.body()
    }
    suspend fun updateDetalleProductoSucursal(detalleProductoSucursalesDto: DetalleProductoSucursalesDto): DetalleProductoSucursalesDto {
        return client.put("$baseUrl/api/DetalleProductoSucursales/${detalleProductoSucursalesDto.detalleProductoSucursalId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(detalleProductoSucursalesDto)
        }.body()
    }
    suspend fun deleteDetalleProductoSucursal(id: Int): DetalleProductoSucursalesDto {
        return client.delete("$baseUrl/api/DetalleProductoSucursales/$id").body()
    }

    // DetalleVentaPagos
    suspend fun getDetalleVentaPagos(): List<DetalleVentaPagosDto> {
        return client.get("$baseUrl/api/DetalleVentaPagos").body()
    }
    suspend fun getDetalleVentaPagoById(id: Int): DetalleVentaPagosDto {
        return client.get("$baseUrl/api/DetalleVentaPagos/$id").body()
    }
    suspend fun addDetalleVentaPago(detalleVentaPagosDto: DetalleVentaPagosDto): DetalleVentaPagosDto {
        return client.post("$baseUrl/api/DetalleVentaPagos") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(detalleVentaPagosDto)
        }.body()
    }
    suspend fun updateDetalleVentaPago(detalleVentaPagosDto: DetalleVentaPagosDto): DetalleVentaPagosDto {
        return client.put("$baseUrl/api/DetalleVentaPagos/${detalleVentaPagosDto.detalleVentaPagoId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(detalleVentaPagosDto)
        }.body()
    }
    suspend fun deleteDetalleVentaPago(id: Int): DetalleVentaPagosDto {
        return client.delete("$baseUrl/api/DetalleVentaPagos/$id").body()
    }

    // DetalleVentas
    suspend fun getDetalleVentas(): List<DetalleVentasDto> {
        return client.get("$baseUrl/api/DetalleVentas").body()
    }
    suspend fun getDetalleVentaById(id: Int): DetalleVentasDto {
        return client.get("$baseUrl/api/DetalleVentas/$id").body()
    }
    suspend fun addDetalleVenta(detalleVentasDto: DetalleVentasDto): DetalleVentasDto {
        return client.post("$baseUrl/api/DetalleVentas") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(detalleVentasDto)
        }.body()
    }
    suspend fun updateDetalleVenta(detalleVentasDto: DetalleVentasDto): DetalleVentasDto {
        return client.put("$baseUrl/api/DetalleVentas/${detalleVentasDto.detalleVentaId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(detalleVentasDto)
        }.body()
    }
    suspend fun deleteDetalleVenta(id: Int): DetalleVentasDto {
        return client.delete("$baseUrl/api/DetalleVentas/$id").body()
    }

    // Ventas
    suspend fun getVentas(): List<VentasDto> {
        return client.get("$baseUrl/api/Ventas").body()
    }
    suspend fun getVentaById(id: Int): VentasDto {
        return client.get("$baseUrl/api/Ventas/$id").body()
    }
    suspend fun addVenta(ventasDto: VentasDto): VentasDto {
        return client.post("$baseUrl/api/Ventas") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(ventasDto)
        }.body()
    }
    suspend fun updateVenta(ventasDto: VentasDto): VentasDto {
        return client.put("$baseUrl/api/Ventas/${ventasDto.ventaId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(ventasDto)
        }.body()
    }
    suspend fun deleteVenta(id: Int): VentasDto {
        return client.delete("$baseUrl/api/Ventas/$id").body()
    }

    // Verificaciones
    suspend fun getVerificaciones(): List<VerificacionesDto> {
        return client.get("$baseUrl/api/Verificaciones").body()
    }
    suspend fun getVerificacionById(id: Int): VerificacionesDto {
        return client.get("$baseUrl/api/Verificaciones/$id").body()
    }
    suspend fun addVerificacion(verificacionesDto: VerificacionesDto): VerificacionesDto {
        return client.post("$baseUrl/api/Verificaciones") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(verificacionesDto)
        }.body()
    }
    suspend fun updateVerificacion(verificacionesDto: VerificacionesDto): VerificacionesDto {
        return client.put("$baseUrl/api/Verificaciones/${verificacionesDto.verificacionId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(verificacionesDto)
        }.body()
    }
    suspend fun deleteVerificacion(id: Int): VerificacionesDto {
        return client.delete("$baseUrl/api/Verificaciones/$id").body()
    }

    // Usuarios
    suspend fun getUsuarios(): List<UsuariosDto> {
        return client.get("$baseUrl/api/Usuarios").body()
    }
    suspend fun getUsuarioById(id: Int): UsuariosDto {
        return client.get("$baseUrl/api/Usuarios/$id").body()
    }
    suspend fun addUsuario(usuarioDto: UsuariosDto): UsuariosDto {
        val response = client.post("$baseUrl/api/Usuarios") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(usuarioDto)
        }
        return response.body()
    }
    suspend fun updateUsuario(usuariosDto: UsuariosDto): UsuariosDto {
        return client.put("$baseUrl/api/Usuarios/${usuariosDto.usuarioId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(usuariosDto)
        }.body()
    }
    suspend fun deleteUsuario(id: Int): UsuariosDto {
        return client.delete("$baseUrl/api/Usuarios/$id").body()
    }

    // Favoritos
    suspend fun getFavoritos(): List<FavoritosDto> {
        return client.get("$baseUrl/api/Favoritos").body()
    }
    suspend fun getFavoritoById(id: Int): FavoritosDto {
        return client.get("$baseUrl/api/Favoritos/$id").body()
    }
    suspend fun addFavorito(favoritosDto: FavoritosDto): FavoritosDto {
        return client.post("$baseUrl/api/Favoritos") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(favoritosDto)
        }.body()
    }
    suspend fun updateFavorito(favoritosDto: FavoritosDto): FavoritosDto {
        return client.put("$baseUrl/api/Favoritos/${favoritosDto.favoritoId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(favoritosDto)
        }.body()
    }
    suspend fun deleteFavorito(id: Int): FavoritosDto {
        return client.delete("$baseUrl/api/Favoritos/$id").body()
    }

    // Iconos
    suspend fun getIconos(): List<IconosDto> {
        return client.get("$baseUrl/api/Iconos").body()
    }
    suspend fun getIconoById(id: Int): IconosDto {
        return client.get("$baseUrl/api/Iconos/$id").body()
    }
    suspend fun addIcono(iconosDto: IconosDto): IconosDto {
        return client.post("$baseUrl/api/Iconos") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(iconosDto)
        }.body()
    }
    suspend fun updateIcono(iconosDto: IconosDto): IconosDto {
        return client.put("$baseUrl/api/Iconos/${iconosDto.iconoId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(iconosDto)
        }.body()
    }
    suspend fun deleteIcono(id: Int): IconosDto {
        return client.delete("$baseUrl/api/Iconos/$id").body()
    }

    // Reclamaciones
    suspend fun getReclamaciones(): List<ReclamacionesDto> {
        return client.get("$baseUrl/api/Reclamaciones").body()
    }
    suspend fun getReclamacionById(id: Int): ReclamacionesDto {
        return client.get("$baseUrl/api/Reclamaciones/$id").body()
    }
    suspend fun addReclamacion(reclamacionesDto: ReclamacionesDto): ReclamacionesDto {
        return client.post("$baseUrl/api/Reclamaciones") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(reclamacionesDto)
        }.body()
    }
    suspend fun updateReclamacion(reclamacionesDto: ReclamacionesDto): ReclamacionesDto {
        return client.put("$baseUrl/api/Reclamaciones/${reclamacionesDto.reclamacionId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(reclamacionesDto)
        }.body()
    }
    suspend fun deleteReclamacion(id: Int): ReclamacionesDto {
        return client.delete("$baseUrl/api/Reclamaciones/$id").body()
    }

    // Roles
    suspend fun getRoles(): List<RolesDto> {
        return client.get("$baseUrl/api/Roles").body()
    }
    suspend fun getRolById(id: Int): RolesDto {
        return client.get("$baseUrl/api/Roles/$id").body()
    }
    suspend fun addRol(rolesDto: RolesDto): RolesDto {
        return client.post("$baseUrl/api/Roles") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(rolesDto)
        }.body()
    }
    suspend fun updateRol(rolesDto: RolesDto): RolesDto {
        return client.put("$baseUrl/api/Roles/${rolesDto.rolId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(rolesDto)
        }.body()
    }
    suspend fun deleteRol(id: Int): RolesDto {
        return client.delete("$baseUrl/api/Roles/$id").body()
    }

    // Sucursales
    suspend fun getSucursales(): List<SucursalesDto> {
        return client.get("$baseUrl/api/Sucursales").body()
    }
    suspend fun getSucursalById(id: Int): SucursalesDto {
        return client.get("$baseUrl/api/Sucursales/$id").body()
    }
    suspend fun addSucursal(sucursalesDto: SucursalesDto): SucursalesDto {
        return client.post("$baseUrl/api/Sucursales") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(sucursalesDto)
        }.body()
    }
    suspend fun updateSucursal(sucursalesDto: SucursalesDto): SucursalesDto {
        return client.put("$baseUrl/api/Sucursales/${sucursalesDto.sucursalId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(sucursalesDto)
        }.body()
    }
    suspend fun deleteSucursal(id: Int): SucursalesDto {
        return client.delete("$baseUrl/api/Sucursales/$id").body()
    }

    // Tarifas
    suspend fun getTarifas(): List<TarifasDto> {
        return client.get("$baseUrl/api/Tarifas").body()
    }
    suspend fun getTarifaById(id: Int): TarifasDto {
        return client.get("$baseUrl/api/Tarifas/$id").body()
    }
    suspend fun addTarifa(tarifasDto: TarifasDto): TarifasDto {
        return client.post("$baseUrl/api/Tarifas") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(tarifasDto)
        }.body()
    }
    suspend fun updateTarifa(tarifasDto: TarifasDto): TarifasDto {
        return client.put("$baseUrl/api/Tarifas/${tarifasDto.tarifaId}") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(tarifasDto)
        }.body()
    }
    suspend fun deleteTarifa(id: Int): TarifasDto {
        return client.delete("$baseUrl/api/Tarifas/$id").body()
    }
}

