package com.example.cookingbook.ui.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingbook.ui.models.RecetteViewModel

class RecetteViewModelFactory (private val repository: RecetteRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(RecetteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RecetteViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel inconnu: ${modelClass.name}")
    }
}