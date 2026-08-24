package com.example.cookingbook.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Room DAO for the 'recettes' table. All write operations are 'suspended' functions (safe to call
 * from a coroutine, e.g. within [androidx.lifecycle.viewModelScope]), the read query returns a [Flow]
 * so Compose UI observing it recomposes automatically whenever the underlying data changes, without
 * any manual refresh logic.
 */
@Dao
interface RecetteRequests {

    /**
     * Streams the full recipe list, alphabetically ordered by title. Re-emits automatically whenever
     * the 'recettes' table changes (Compose UI collecting its flow updates itself with no manual
     * refresh needed).
     */
    @Query("SELECT * FROM recettes ORDER BY titre ASC")
    fun getAllRecettes(): Flow<List<Recette>>

    /**
     * Fetches a single recipe by [id].
     *
     * @return the matching [Recette], or 'null' if no recipe with that id exists. Currently unused
     * by the rest of the app (recipe lookups elsewhere (e.g. 'NavBar.kt''s recipe detail route) go
     * through [getAllRecettes]'s in-memory list and 'find {it.id == ...}' instead of calling this
     * directly).
     */
    @Query("SELECT * FROM recettes WHERE id = :id")
    suspend fun getRecetteById(id: Long): Recette?

    /**
     * Inserts [recette]. On a primary key conflict, the existing row is replaced entirely rather than
     * the insert failing (relevant mainly if a [Recette] with an explicit, already-used 'id' is ever
     * inserted (auto-generated ids from new recipes won't conflict)).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recette: Recette)

    /** Updates the row matching [recette]'s 'id' with its other field values. */
    @Update
    suspend fun update(recette: Recette)

    /** Deletes the row matching [recette]'s 'id'. */
    @Delete
    suspend fun delete(recette: Recette)
}