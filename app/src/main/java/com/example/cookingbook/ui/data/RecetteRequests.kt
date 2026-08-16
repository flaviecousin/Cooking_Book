package com.example.cookingbook.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetteRequests {
    @Query("SELECT * FROM recettes ORDER BY titre ASC")
    fun getAllRecettes(): Flow<List<Recette>>
    // UI Compose automatically updates once the data change

    @Query("SELECT * FROM recettes WHERE id = :id")
    suspend fun getRecetteById(id: Long): Recette?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recette: Recette)

    @Update
    suspend fun update(recette: Recette)

    @Delete
    suspend fun delete(recette: Recette)
}