package com.example.cookingbook.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recette::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun recetteDao(): RecetteRequests

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

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