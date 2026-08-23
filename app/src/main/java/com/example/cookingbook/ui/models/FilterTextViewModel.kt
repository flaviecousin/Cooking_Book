package com.example.cookingbook.ui.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Lightweight ViewModel powering the recipe title search bar ([com.example.cookingbook.ui.components.SearchBar]).
 *
 * Unlike [RecetteViewModel], this one holds no persisted or reactive data source of its own (it's a
 * pure, in-memory filtering helper). The caller is responsible for keeping [allTitles] in sync
 * (typically via a 'LaunchedEffect' re-running [setTitles] whenever the underlying recipe list changes),
 * and for feeding user input into [filterText] on every keystroke
 *
 * Instantiated per-composable via 'viewModel()' (see 'SearchBar.kt') rather than shared app-wide,since
 * search state is local to wherever the search is shown.
 */
class FilterTextViewModel : ViewModel(){
    /** The full, unfiltered pool of recipe titles to search against */
    private var allTitles: List<String> = emptyList()
    private val _filteredItems = MutableStateFlow<List<String>>(emptyList())

    /**
     * Titles from [allTitles] currently matching the last query passed to [filterText]. Empty both
     * when there's no match and when the query is blank (the UI treats both cases the same way (no
     * results dropdown)).
     */
    val filteredItems: StateFlow<List<String>> = _filteredItems

    /**
     * Replaces the pool of titles available for filtering. Does **not** re-run the last filter
     * against the new pool - a stale [filteredItems] value persists until [filterText] is called again.
     *
     * @param titles the full list of recipe titles to search against.
     */
    fun setTitles(titles: List<String>){
        allTitles = titles
    }

    /**
     * Filters [allTitles] by [input], case-insensitivity, using a simple substring match (no fuzzy
     * matching, no ranking by relevance).
     *
     * @param input the current search bar text. A blank input clears [filteredItems] entirely rather
     * than returning all titles, so no results dropdown appears when the fields is empty.
     */
    fun filterText(input: String){
        _filteredItems.value = if (input.isBlank()) {
            emptyList()
        }
        else{
            allTitles.filter{ it.contains(input, ignoreCase = true) }
        }
    }
}