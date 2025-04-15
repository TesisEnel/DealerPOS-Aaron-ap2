@file:OptIn(InternalAPI::class)

package org.aarondeveloper.dealerpos.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.call.body
import io.ktor.util.InternalAPI
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.CiudadesDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.EstadosDto
import org.aarondeveloper.dealerpos.data.remote.dto.geografiaapi.PaisesDto

class GeografiaApi(private val client: HttpClient, private val baseUrl: String) {

    // Paises
    suspend fun getPaises(): List<PaisesDto> {
        return client.get("$baseUrl/api/Paises").body()
    }

    suspend fun getPaisById(id: Int): PaisesDto {
        return client.get("$baseUrl/api/Paises/$id").body()
    }

    suspend fun addPais(paisesDto: PaisesDto): PaisesDto {
        return client.post("$baseUrl/api/Paises") {
            body = paisesDto
        }.body()
    }

    suspend fun updatePais(paisesDto: PaisesDto): PaisesDto {
        return client.put("$baseUrl/api/Paises") {
            body = paisesDto
        }.body()
    }

    suspend fun deletePais(id: Int): PaisesDto {
        return client.delete("$baseUrl/api/Paises/$id").body()
    }

    // Ciudades
    suspend fun getCiudades(): List<CiudadesDto> {
        return client.get("$baseUrl/api/Ciudades").body()
    }

    suspend fun getCiudadById(id: Int): CiudadesDto {
        return client.get("$baseUrl/api/Ciudades/$id").body()
    }

    suspend fun addCiudad(ciudadesDto: CiudadesDto): CiudadesDto {
        return client.post("$baseUrl/api/Ciudades") {
            body = ciudadesDto
        }.body()
    }

    suspend fun updateCiudad(ciudadesDto: CiudadesDto): CiudadesDto {
        return client.put("$baseUrl/api/Ciudades") {
            body = ciudadesDto
        }.body()
    }

    suspend fun deleteCiudad(id: Int): CiudadesDto {
        return client.delete("$baseUrl/api/Ciudades/$id").body()
    }

    // Estados
    suspend fun getEstados(): List<EstadosDto> {
        return client.get("$baseUrl/api/Estados").body()
    }

    suspend fun getEstadoById(id: Int): EstadosDto {
        return client.get("$baseUrl/api/Estados/$id").body()
    }

    suspend fun addEstado(estadosDto: EstadosDto): EstadosDto {
        return client.post("$baseUrl/api/Estados") {
            body = estadosDto
        }.body()
    }

    suspend fun updateEstado(estadosDto: EstadosDto): EstadosDto {
        return client.put("$baseUrl/api/Estados") {
            body = estadosDto
        }.body()
    }

    suspend fun deleteEstado(id: Int): EstadosDto {
        return client.delete("$baseUrl/api/Estados/$id").body()
    }

}

