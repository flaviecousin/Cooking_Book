package com.example.cookingbook.ui.data

/**
 * A single ingredient line within a [Recette] (a free-form quantity paired with a free-form ingredient
 * name(e.g. 'quantite = "200g"', 'ingredient = "farine"')).
 *
 * Both [quantite] and [ingredient] are plain, unvalidated strings rather than structured amount/unit
 * data (this keeps the add-recipe form simple but means quantity can't be reliably parsed, scaled,
 * or unit-converted later without additional parsing logic).
 *
 * @property ingredient the ingredient's name, e.g. '"farine"'.
 * @property quantite the quantity as free text, e.g. '"200g"', '"2 cuillère à soupe"'.
 */
data class Ingredient(
    val id: Int,
    val ingredient: String,
    val quantite: String
)