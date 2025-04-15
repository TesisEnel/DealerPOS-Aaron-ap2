package org.aarondeveloper.dealerpos.di

import org.aarondeveloper.dealerpos.data.local.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()).build().configuracionDao() }
    single { getDatabaseBuilder(get()).build().carritoDao() }
    single { getDatabaseBuilder(get()).build().detalleCarritoAdicionalDao() }
}