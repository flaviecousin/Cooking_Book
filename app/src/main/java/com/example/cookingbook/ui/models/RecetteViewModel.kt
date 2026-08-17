package com.example.cookingbook.ui.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingbook.ui.data.Recette
import com.example.cookingbook.ui.data.RecetteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecetteViewModel(private val repository: RecetteRepository) : ViewModel() {
    val recettes: StateFlow<List<Recette>> = repository.allRecettes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    fun ajouterRecette(recette: Recette){
        viewModelScope.launch {
            repository.insert(recette)
        }
    }
}