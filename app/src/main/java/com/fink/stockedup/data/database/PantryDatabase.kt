package com.fink.stockedup.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fink.stockedup.data.dao.PantryDao
import com.fink.stockedup.data.entity.PantryItem

@Database(entities = [PantryItem::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PantryDatabase : RoomDatabase() {
    abstract fun pantryDao(): PantryDao

    companion object {
        @Volatile
        private var INSTANCE: PantryDatabase? = null

        fun getDatabase(context: Context): PantryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PantryDatabase::class.java,
                    "pantry_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
