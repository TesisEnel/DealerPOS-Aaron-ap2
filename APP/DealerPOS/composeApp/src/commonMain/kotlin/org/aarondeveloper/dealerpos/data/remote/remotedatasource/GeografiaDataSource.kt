package org.aarondeveloper.dealerpos.data.remote.remotedatasource


import org.aarondeveloper.dealerpos.data.remote.api.GeografiaApi
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.CiudadesDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.EstadosDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.PaisesDto

class GeografiaDataSource(private val api: GeografiaApi) {

    // Paises
    suspend fun getPaises(): List<PaisesDto> = api.getPaises()
    suspend fun getPaisById(id: Int) = api.getPaisById(id)
    suspend fun addPais(paisesDto: PaisesDto) = api.addPais(paisesDto)
    suspend fun updatePais(paisesDto: PaisesDto) = api.updatePais(paisesDto)
    suspend fun deletePais(id: Int) = api.deletePais(id)

    // Ciudades
    suspend fun getCiudades(): List<CiudadesDto> = api.getCiudades()
    suspend fun getCiudadById(id: Int) = api.getCiudadById(id)
    suspend fun addCiudad(ciudadesDto: CiudadesDto) = api.addCiudad(ciudadesDto)
    suspend fun updateCiudad(ciudadesDto: CiudadesDto) = api.updateCiudad(ciudadesDto)
    suspend fun deleteCiudad(id: Int) = api.deleteCiudad(id)

    // Estados
    suspend fun getEstados(): List<EstadosDto> = api.getEstados()
    suspend fun getEstadoById(id: Int) = api.getEstadoById(id)
    suspend fun addEstado(estadosDto: EstadosDto) = api.addEstado(estadosDto)
    suspend fun updateEstado(estadosDto: EstadosDto) = api.updateEstado(estadosDto)
    suspend fun deleteEstado(id: Int) = api.deleteEstado(id)
}
