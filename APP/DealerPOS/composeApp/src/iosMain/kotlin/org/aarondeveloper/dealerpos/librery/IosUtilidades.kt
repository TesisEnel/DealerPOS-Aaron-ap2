package org.aarondeveloper.dealerpos.librery

import androidx.compose.ui.graphics.ImageBitmap
import platform.Foundation.NSBundle
import platform.Foundation.NSCalendar
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSUUID
import platform.Foundation.preferredLanguages
import platform.Foundation.timeIntervalSince1970
import platform.UIKit.UIDevice


actual fun convertirBase64AImageBitmap(base64String: String): ImageBitmap? {
    return try {
        null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

actual fun obtenerCodigo(): String {
    return NSUUID().UUIDString
}

actual fun obtenerDispositivo(): String {
    return UIDevice.currentDevice.name
}

actual fun logDebug(message: String) {
    println(message)
}

actual fun getFechaActual(): String {
    val currentDate = NSDate()
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = "yyyy-MM-dd:HH:mm"
    return dateFormatter.stringFromDate(currentDate)
}

actual fun getFechaVencimientoCodigo(): String {
    val currentDate = NSDate()
    val calendar = NSCalendar.currentCalendar
    val dateComponents = NSDateComponents()
    dateComponents.day = 1
    val expirationDate = calendar.dateByAddingComponents(dateComponents, currentDate, 0u)!!
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = "yyyy-MM-dd:HH:mm"

    return dateFormatter.stringFromDate(expirationDate)
}


actual fun validarFecha(fechaVencimiento: String, fechaActual: String): Boolean {
    return try {
        val formatter = NSDateFormatter().apply {
            dateFormat = "yyyy-MM-dd:HH:mm"
        }
        val vencimiento = formatter.dateFromString(fechaVencimiento)
        val actual = formatter.dateFromString(fechaActual)

        if (vencimiento != null && actual != null) {
            vencimiento.timeIntervalSince1970 > actual.timeIntervalSince1970
        } else {
            false
        }
    } catch (e: Exception) {
        false
    }
}

actual fun getDeviceLanguage(): String {
    val preferredLanguages = NSLocale.preferredLanguages()
    val firstLanguage = if (preferredLanguages.isNotEmpty()) preferredLanguages[0] as String else "en"
    return firstLanguage.takeWhile { it != '-' }
}

actual fun getAppVersion(): String {
    val bundle = NSBundle.mainBundle
    return bundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: "Unknown"
}
