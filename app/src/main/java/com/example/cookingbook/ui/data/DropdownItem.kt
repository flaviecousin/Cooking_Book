package com.example.cookingbook.ui.data

/**
 * Thin wrapper around a display label, used as the item type for
 * [com.example.cookingbook.ui.components.InputCategories]' category dropdown. Exists mainly so the
 * dropdown's item list has a dedicated type to key/compose against, rather than operating on raw [String]s
 * directly.
 */
data class DropdownItem(
    val title: String
)