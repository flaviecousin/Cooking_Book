package com.example.cookingbook.ui.data

import kotlinx.coroutines.flow.Flow

class RecetteRepository(private val dao: RecetteRequests) {
    val allRecettes: Flow<List<Recette>> = dao.getAllRecettes()

    suspend fun insert(recette: Recette) = dao.insert(recette)
    suspend fun delete(recette: Recette) = dao.delete(recette)
}