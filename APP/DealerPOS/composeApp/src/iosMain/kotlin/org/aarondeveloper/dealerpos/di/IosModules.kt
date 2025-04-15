package org.aarondeveloper.dealerpos.di

import org.aarondeveloper.dealerpos.data.local.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder().build().configuracionDao() }
    single { getDatabaseBuilder().build().carritoDao() }
    single { getDatabaseBuilder().build().detalleCarritoAdicionalDao() }
}