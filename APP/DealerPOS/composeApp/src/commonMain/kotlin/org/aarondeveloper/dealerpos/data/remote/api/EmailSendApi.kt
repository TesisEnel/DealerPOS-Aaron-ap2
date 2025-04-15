package org.aarondeveloper.dealerpos.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLBuilder
import kotlinx.serialization.json.Json

class EmailSendApi(private val client: HttpClient, private val baseUrl: String) {

    suspend fun sendEmail(
        asunto: String,
        mensaje: String,
        destinatario: String,
        piePagina: String,
        nombreEmpresa: String,
        direccionEmpresa: String,
        smtpHost: String,
        smtpUsername: String,
        smtpPassword: String,
        smtpPort: Int
    ): String {
        val url = URLBuilder(baseUrl).apply {
            parameters.append("asunto", asunto)
            parameters.append("mensaje", mensaje)
            parameters.append("destinatario", destinatario)
            parameters.append("piePagina", piePagina)
            parameters.append("nombreEmpresa", nombreEmpresa)
            parameters.append("direccionEmpresa", direccionEmpresa)
            parameters.append("smtpHost", smtpHost)
            parameters.append("smtpUsername", smtpUsername)
            parameters.append("smtpPassword", smtpPassword)
            parameters.append("smtpPort", smtpPort.toString())
        }.build()

        return try {
            val res = client.get(url).bodyAsText()
            val responseJson = Json.decodeFromString<Map<String, String>>(res)
            val validacion = responseJson["Validacion"]

            if (validacion == "Exitoso") {
                validacion
            } else {
                "Error en el envío del correo"
            }

        } catch (e: ClientRequestException) {
            "Error en la solicitud: ${e.response.status}"
        } catch (e: ResponseException) {
            "Error en la respuesta: ${e.message}"
        } catch (e: Exception) {
            "Error general: ${e.message}"
        }
    }
}
