package com.fink.stockedup.data.database

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase
import com.fink.stockedup.data.dao.PantryDao
import com.fink.stockedup.data.entity.PantryItem

@Database(entities = [PantryItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PantryDatabase : RoomDatabase() {

    // 🔹 DAO access
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
                )
                    .addTypeConverter(Converters())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
