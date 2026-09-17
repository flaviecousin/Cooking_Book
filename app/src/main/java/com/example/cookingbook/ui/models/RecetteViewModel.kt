package com.example.cookingbook.ui.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingbook.ui.data.Recette
import com.example.cookingbook.ui.data.RecetteRepository
import com.example.cookingbook.ui.data.deleteInternalImage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Central ViewModel exposing the app's recipe data and mutation operations to the UI layer. Every
 * screen that reads or writes recipes (grid, detail, add/edit) goes through this single ViewModel
 * instance, created once in [com.example.cookingbook.MainActivity] and shared across the navigation
 * graph.
 *
 * All write operations ([ajouterRecette], [supprimerRecette], [modifierRecette]) share the same shape:
 * they run on [viewModelScope], log the exception via [Throwable.printStackTrace] and report failure
 * to the UI through an optional [onError] callback, and take an optional [onSuccess] callback used
 * by callers to chain UI feedback (e.g. showing a snackbar, resetting a form, or navigating back)
 * only once the operation has actually completed (important since Room operations are suspending and
 * shouldn't be assumed to finish synchronously).
 *
 * @param repository the data access layer this ViewModel delegates to.
 */
class RecetteViewModel(private val repository: RecetteRepository) : ViewModel() {
    /**
     * Live, observable list of all recipes, sourced from [RecetteRepository.allRecettes] and
     * converted from a cold [kotlinx.coroutines.flow.Flow] into a hot [StateFlow] via [stateIn].
     *
     * Uses [SharingStarted.WhileSubscribed] with a 5-second grace period: the underlying Room query
     * flow stays active for 5 seconds after the last collector disappears (e.g. during a brief
     * configuration change or screen transition) before being torn down, avoiding unnecessary query
     * restarts. Starts as an empty list before the first emission arrives from the database.
     */
    val recettes: StateFlow<List<Recette>> = repository.allRecettes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    /**
     * Persists a new [recette] via [RecetteRepository.insert].
     *
     * @param recette the recipe to create.
     * @param onSuccess invoked after a successful insert; callers typically use this to reset the
     * add form and show a confirmation message.
     * @param onError invoked with a user-facing message if the add fails. Callers typically use
     * this to show a snackbar. The recipe list is left unchanged and no navigation occurs.
     */
    fun ajouterRecette(recette: Recette, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}){
        viewModelScope.launch {
            try{
                repository.insert(recette)
                onSuccess()
            }
            catch (e: Exception){
                e.printStackTrace()
                onError("Impossible d'enregistrer la recette, veuillez réessayer.")
            }
        }
    }

    /**
     * Permanently deletes [recette] via [RecetteRepository.delete], and cleans up its associated photo
     * file (see [deleteInternalImage]) so deleting a recipe doesn't leave an orphaned image in internal
     * storage. There is no undo (the confirmation step lives in the UI layer (see the delete
     * [androidx.compose.material3.AlertDialog] in 'RecipeScreen.kt'), not here).
     *
     * @param recette the recipe to delete
     * @param onSuccess invoked after a successful delete; callers typically use this to navigate
     * back to the recipe grid.
     * @param onError invoked with a user-facing message if the delete fails. Callers typically use
     * this to show a snackbar. The recipe list is left unchanged and no navigation occurs.
     */
    fun supprimerRecette(recette: Recette, onSuccess: () -> Unit = {}, onError: (String) -> Unit){
        viewModelScope.launch {
            try{
                repository.delete(recette)
                deleteInternalImage(recette.image)
                onSuccess()
            }
            catch (e: Exception){
                e.printStackTrace()
                onError("Impossible de supprimer la recette, veuillez réessayer.")
            }
        }
    }

    /**
     * Persists change to an existing [recette] via [RecetteRepository.update]. Used by 'AddScreen.kt'
     * when operating in edit mode (i.e. called with a non-null existing recipe rather than a
     * newly-built blank one).
     *
     * @param recette the recipe with updated field values (must retain its original 'id' for Room to
     * match the existing row).
     * @param onSuccess invoked after a successful update; callers typically use this to show a
     * confirmation message and navigate back to the recipe detail screen.
     * @param onError invoked with a user-facing message if the modification fails. Callers typically
     * use this to show a snackbar. The recipe list is left unchanged and no navigation occurs.
     */
    fun modifierRecette(recette: Recette, onSuccess: () -> Unit = {}, onError: (String) -> Unit){
        viewModelScope.launch {
            try{
                repository.update(recette)
                onSuccess()
            }
            catch (e: Exception){
                e.printStackTrace()
                onError("Impossible de modifier la recette, veuillez réessayer.")
            }
        }
    }
}