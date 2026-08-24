package com.example.cookingbook.ui.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingbook.ui.models.RecetteViewModel

/**
 * [ViewModelProvider.Factory] for [RecetteViewModel], needed because that ViewModel takes a [RecetteRepository]
 * constructor argument (the default factory used by 'viewModel()' can only instantiate ViewModels with
 * a no-argument constructor, so this manuel factory supplies the dependency instead).
 *
 * Constructed once in [com.example.cookingbook.MainActivity] alongside the repository it wraps, and
 * passed to 'viewModel(factory = ...)' when obtaining the shared [RecetteViewModel] instance.
 *
 * @param repository the repository instance to inject into any created [RecetteViewModel].
 */
class RecetteViewModelFactory (private val repository: RecetteRepository): ViewModelProvider.Factory{
    /**
     * Creates a new viewModel instance of the requested [modelClass].
     *
     * @throws IllegalArgumentException if [modelClass] is anything other than [RecetteViewModel]
     * (this factory only knows how to build that one ViewModel type).
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(RecetteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RecetteViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel inconnu: ${modelClass.name}")
    }
}