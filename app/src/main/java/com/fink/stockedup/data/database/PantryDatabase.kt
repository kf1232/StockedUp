package com.fink.stockedup.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fink.stockedup.data.dao.*
import com.fink.stockedup.data.entity.*

@Database(
    entities = [Item::class, PantryItem::class, RecipeList::class, RecipeItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PantryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun pantryItemDao(): PantryItemDao
    abstract fun recipeListDao(): RecipeListDao
    abstract fun recipeItemDao(): RecipeItemDao

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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
