package com.example.cookingbook.ui.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilterTextViewModel : ViewModel(){
    private val items = listOf(
        "Gâteau",
        "Tarte",
        "Brioche",
        "Viande",
        "Poisson",
        "Barbecue"
    )
    private val _filteredItems = MutableStateFlow(items)
    var filteredItems: StateFlow<List<String>> = _filteredItems

    fun filterText(input: String){
        _filteredItems.value = items.filter{
            it.contains(input, ignoreCase = true)
        }
    }
}