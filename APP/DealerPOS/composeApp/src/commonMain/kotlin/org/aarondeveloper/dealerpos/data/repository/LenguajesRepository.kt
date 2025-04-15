package org.aarondeveloper.dealerpos.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.aarondeveloper.dealerpos.librery.Resource
import org.aarondeveloper.dealerpos.librery.getDeviceLanguage
import org.aarondeveloper.dealerpos.ui.languages.StringsEn
import org.aarondeveloper.dealerpos.ui.languages.StringsEs


class LenguajesRepository {

    fun getDeviceLanguages(): String {
        return getDeviceLanguage()
    }

    fun getLenguages(): Flow<Resource<Any>> = flow {
        emit(Resource.Loading())

        val deviceLanguage = try {
            getDeviceLanguages()
        } catch (e: Exception) {
            "en"
        }

        val languageFileContent = try {
            when (deviceLanguage) {
                "en" -> StringsEn()
                "es" -> StringsEs()
                else -> StringsEn()
            }
        } catch (e: Exception) {
            emit(Resource.Error("No se pudo cargar el archivo de idioma"))
            return@flow
        }

        try {
            emit(Resource.Success(languageFileContent))
        } catch (e: Exception) {
            emit(Resource.Error("Error al procesar el archivo de idioma"))
        }
    }
}



