package com.example.cookingbook.ui.data

/**
 * A single numbered step in a [Recette]'s preparation instructions.
 *
 * @property numero the step's display order. Mirrors the item's position in the list at creation time
 * (see 'AddStepsRecipe.kt''s' 'AddSteps', which assigns 'maxOf(numero) + 1' to new steps) rather than
 * being recomputed from list index on every render (so it also doubles as a stable identity key for
 * Compose ('key(preparation.numero)') independent of reordering).
 * @property etape the step's instruction text.
 */
data class Preparation(
    val numero: Int,
    val etape: String
)
