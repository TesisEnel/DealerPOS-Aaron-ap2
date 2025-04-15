package org.aarondeveloper.dealerpos

import android.app.Application
import org.aarondeveloper.dealerpos.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class DealerPosApp : Application() {

    companion object {
        lateinit var instance: DealerPosApp
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DealerPosApp)
        }
    }

}

