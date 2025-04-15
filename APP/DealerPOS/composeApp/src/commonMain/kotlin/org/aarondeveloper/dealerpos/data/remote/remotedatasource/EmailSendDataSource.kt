package org.aarondeveloper.dealerpos.data.remote.remotedatasource


import org.aarondeveloper.dealerpos.data.remote.api.EmailSendApi

class EmailSendDataSource(private val api: EmailSendApi) {

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
        return api.sendEmail(
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
    }
}
