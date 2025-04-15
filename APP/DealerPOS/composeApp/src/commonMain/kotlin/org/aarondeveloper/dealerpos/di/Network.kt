package org.aarondeveloper.dealerpos.di

import io.ktor.client.*
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.aarondeveloper.dealerpos.data.remote.api.DealerPosApi
import org.aarondeveloper.dealerpos.data.remote.api.EmailSendApi
import org.aarondeveloper.dealerpos.data.remote.api.GeografiaApi

const val BASE_URL_API1 = "https://apidiler-hefjduh8ctcecaek.canadacentral-01.azurewebsites.net"
const val BASE_URL_API2 = "https://apigeo-ejacbzcme8g3a6fy.canadacentral-01.azurewebsites.net"
const val BASE_URL_API3 = "https://emailsendpro.azurewebsites.net/api/ControlCorreoControllers.php"

fun provideKtorClient(): HttpClient {
    return HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }
}


fun provideDealerPosApi(client: HttpClient): DealerPosApi {
    return DealerPosApi(client, BASE_URL_API1)
}

fun provideGeograficaApi(client: HttpClient): GeografiaApi {
    return GeografiaApi(client, BASE_URL_API2)
}

fun provideEmailSendApi(client: HttpClient): EmailSendApi {
    return EmailSendApi(client, BASE_URL_API3)
}