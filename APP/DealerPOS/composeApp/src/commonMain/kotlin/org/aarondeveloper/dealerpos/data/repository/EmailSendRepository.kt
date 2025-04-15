package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.EmailSendDataSource
import org.aarondeveloper.dealerpos.librery.Resource

class EmailSendRepository(
    private val emailSendDataSource: EmailSendDataSource
) {

    fun sendEmail(
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
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = emailSendDataSource.sendEmail(
                asunto,
                mensaje,
                destinatario,
                piePagina,
                nombreEmpresa,
                direccionEmpresa,
                smtpHost,
                smtpUsername,
                smtpPassword,
                smtpPort
            )

            if (response == "Exitoso") {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error al enviar el correo"))
        }
    }.flowOn(Dispatchers.IO)
}

