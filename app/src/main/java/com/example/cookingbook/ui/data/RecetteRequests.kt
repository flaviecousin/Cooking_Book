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
 *
 * There is intentionnally no by-id lookup query here: the app already keeps the memory via [getAllRecettes]
 * (collected as a [kotlinx.coroutines.flow.StateFlow] in [com.example.cookingbook.ui.models.RecetteViewModel]),
 * so screens needing a single recipe (see 'NavBar.kt') simply 'find' it in that list instead of issuing
 * a separate query. This also keeps those screens reactive to edits made elsewhere, which a one-shot
 * by-id query would not.
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