package org.aarondeveloper.dealerpos.librery

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.aarondeveloper.dealerpos.DealerPosApp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun convertirBase64AImageBitmap(base64String: String): ImageBitmap? {
    return try {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        bitmap?.asImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@SuppressLint("HardwareIds")
actual fun obtenerCodigo(): String {
    return Settings.Secure.getString(DealerPosApp.instance.contentResolver, Settings.Secure.ANDROID_ID)
}

actual fun obtenerDispositivo(): String {
    return Build.MODEL
}

actual fun logDebug(message: String) {
    android.util.Log.d("Informacion: ", message)
}

@SuppressLint("NewApi")
actual fun getFechaActual(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm")
    return currentDateTime.format(formatter)
}

@SuppressLint("NewApi")
actual fun getFechaVencimientoCodigo(): String {
    val currentDateTime = LocalDateTime.now().plusDays(1)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm")
    return currentDateTime.format(formatter)
}


@SuppressLint("NewApi")
actual fun validarFecha(fechaVencimiento: String, fechaActual: String): Boolean {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm")
        val vencimiento = LocalDateTime.parse(fechaVencimiento, formatter)
        val actual = LocalDateTime.parse(fechaActual, formatter)

        actual.isBefore(vencimiento)
    } catch (e: Exception) {
        false
    }
}

actual fun getDeviceLanguage(): String {
    return Locale.getDefault().language
}

actual fun getAppVersion(): String {
    val context: Context = DealerPosApp.instance.applicationContext
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
}
