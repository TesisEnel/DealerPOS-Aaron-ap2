package org.aarondeveloper.dealerpos.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<DealerPosDB> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<DealerPosDB>(
        name = dbFilePath,
        factory =  { DealerPosDB::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
}