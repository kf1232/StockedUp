package com.fink.stockedup.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fink.stockedup.data.dao.ItemDao
import com.fink.stockedup.data.dao.PantryItemDao
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.data.entity.PantryItem

@Database(entities = [Item::class, PantryItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class StockedUpDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun pantryItemDao(): PantryItemDao

    companion object {
        @Volatile
        private var INSTANCE: StockedUpDatabase? = null

        fun getDatabase(context: Context): StockedUpDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockedUpDatabase::class.java,
                    "pantry_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
