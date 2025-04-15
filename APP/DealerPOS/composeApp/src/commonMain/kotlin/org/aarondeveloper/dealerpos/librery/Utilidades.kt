package org.aarondeveloper.dealerpos.librery

import androidx.compose.ui.graphics.ImageBitmap

expect fun convertirBase64AImageBitmap(base64String: String): ImageBitmap?

expect fun obtenerCodigo(): String

expect fun obtenerDispositivo(): String

expect fun logDebug(message: String)

expect fun getFechaActual(): String

expect fun getFechaVencimientoCodigo(): String

expect fun validarFecha(fechaVencimiento: String, fechaActual: String): Boolean

expect fun getDeviceLanguage(): String

expect fun getAppVersion(): String