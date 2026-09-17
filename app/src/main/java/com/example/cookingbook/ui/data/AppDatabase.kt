package com.example.cookingbook.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Room database for the app, holding a single 'recettes' table backed by [Recette]. Complex fields
 * ([Recette.ingredients], [Recette.instructions]) are persisted as JSON via [Converters].
 *
 * Accessed exclusively through the thread-safe singleton [getDatabase] (there is no public constructor,
 * so all callers share the same underlying SQLite connection).
 */
@Database(entities = [Recette::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    /** Provides access to recipe queries; see [RecetteRequests]. */
    abstract fun recetteDao(): RecetteRequests

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        /**
         * Returns the app-wide singleton [AppDatabase] instance, creating it on first call.
         * Double-checked locking ('@Volatile' + 'synchronized') ensures only one instance is ever
         * built even if called concurrently from multiple threads.
         *
         * @param context any context; only [Context.getApplicationContext] is retained, avoiding
         * leaking a shorter-lived context (e.g. an Activity).
         * @return the shared [AppDatabase] instance, backed by the 'recettes_database' SQLite file.
         */
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val  instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recettes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}