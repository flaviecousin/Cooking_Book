package com.example.cookingbook.ui.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilterTextViewModel : ViewModel(){
    private var allTitles: List<String> = emptyList()
    private val _filteredItems = MutableStateFlow<List<String>>(emptyList())
    val filteredItems: StateFlow<List<String>> = _filteredItems

    fun setTitles(titles: List<String>){
        allTitles = titles
    }
    fun filterText(input: String){
        _filteredItems.value = if (input.isBlank()) {
            emptyList()
        }
        else{
            allTitles.filter{ it.contains(input, ignoreCase = true) }
        }
    }
}