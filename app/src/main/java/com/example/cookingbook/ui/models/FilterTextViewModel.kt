package com.example.cookingbook.ui.models

import androidx.lifecycle.ViewModel
import com.example.cookingbook.ui.data.Recette
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Lightweight ViewModel powering the recipe title search bar ([com.example.cookingbook.ui.components.SearchBar]).
 *
 * Unlike [RecetteViewModel], this one holds no persisted or reactive data source of its own (it's a
 * pure, in-memory filtering helper). The caller is responsible for keeping [allRecipes] in sync
 * (typically via a 'LaunchedEffect' re-running [setRecipes] whenever the underlying recipe list changes),
 * and for feeding user input into [filterText] on every keystroke
 *
 * Filters and exposes full [Recette] objects rather than plain title strings, precisely so that
 * tapping a search result never needs a second, title-based lookup: 2 recipes sharing the same exact
 * title (matched independently, since [Recette.titre] carries no uniqueness constraint) stay
 * distinguishable because each filtered entry still carries its own unique 'id'.
 */
class FilterTextViewModel : ViewModel(){
    /** The full, unfiltered pool of recipe to search against */
    private var allRecipes: List<Recette> = emptyList()

    /**
     * The last search query passed to [filterText], kept so [allRecipes] can reapply it whenever
     * the recipe pool changes (e.g. after an add/edit/delete while a search is active)
     */
    private var currentQuery: String = ""
    private val _filteredItems = MutableStateFlow<List<Recette>>(emptyList())

    /**
     * Recipes from [allRecipes] currently matching the last query passed to [filterText]. Empty both
     * when there's no match and when the query is blank (the UI treats both cases the same way (no
     * results dropdown)).
     */
    val filteredItems: StateFlow<List<Recette>> = _filteredItems

    /**
     * Replaces the pool of recipes available for filtering, and immediately reapplies [currentQuery]
     * against the new pool (unlike the previous title-only implementation, a search stays live and
     * correct across recipe list changes instead of going stale until the next keystroke).
     *
     * @param recipes the full list of recipes to search against.
     */
    fun setRecipes(recipes: List<Recette>){
        allRecipes = recipes
        applyFilter()
    }

    /**
     * Filters [allRecipes] by [input] on title, case-insensitivity, using a simple substring match
     * (no fuzzy matching, no ranking by relevance).
     *
     * @param input the current search bar text. A blank input clears [filteredItems] entirely rather
     * than returning every recipe, so no results dropdown appears when the field is empty.
     */
    fun filterText(input: String){
        currentQuery = input
        applyFilter()
    }

    private fun applyFilter(){
        _filteredItems.value = if (currentQuery.isBlank()){
            emptyList()
        } else {
            allRecipes.filter { it.titre.contains(currentQuery, ignoreCase = true) }
        }
    }
}