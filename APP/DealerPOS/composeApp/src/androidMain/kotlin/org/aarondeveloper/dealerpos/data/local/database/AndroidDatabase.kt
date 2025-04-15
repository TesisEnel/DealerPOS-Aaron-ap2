package org.aarondeveloper.dealerpos.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DealerPosDB> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<DealerPosDB>(
        context = appContext,
        name = dbFile.absolutePath
    )
}