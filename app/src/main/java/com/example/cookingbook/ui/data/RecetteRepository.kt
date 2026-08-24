package com.example.cookingbook.ui.data

import kotlinx.coroutines.flow.Flow

/**
 * Data access layer sitting between [RecetteViewModel] [com.example.cookingbook.ui.models.RecetteViewModel]
 * and the Room [RecetteRequests] DAO. Currently a thin pass-through with no added logic (no catching,
 * no mapping, no error handling of its own). Its value is mainly architectural: it keeps the ViewModel
 * decoupled from Room specifics, and gives a single seam where caching, remote sync, or validation
 * could be introduced later without touching the ViewModel.
 *
 * @param dao the Room DAO this repository delegates all operations to.
 */
class RecetteRepository(private val dao: RecetteRequests) {
    /** All recipes, ordered by title; see [RecetteRequests.getAllRecettes]. */
    val allRecettes: Flow<List<Recette>> = dao.getAllRecettes()

    /** Inserts (or replaces, on id conflict) [recette]. */
    suspend fun insert(recette: Recette) = dao.insert(recette)

    /** Deletes [recette] permanently. */
    suspend fun delete(recette: Recette) = dao.delete(recette)

    /** Persists changes to an existing [recette] (matched by its 'id'). */
    suspend fun update(recette:Recette) = dao.update(recette)
}