package com.example.cookingbook.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Corner radius scale. Note: based on the shared code, [lg] is currently only applied to
 * [com.example.cookingbook.ui.components.RecipeCard] (the photo picker (WidgetImg) actually uses [sm],
 * and the recipe detail hero image isn't clipped/rounded at all.)
 */
object Radius {
    // Photo picker button (WidgetImg), small info cards (NumberCard)
    val sm = 6.dp

    /*
    Text fields, dropdowns, buttons (InputTexte, AddIngredient, AddStepsRecipe, InputCategories,
    InputTimes, SavingButton, AdviceCard, TotalTimeCard)
     */
    val md = 12.dp

    // Recipe grid card corners (RecipeCard)
    val lg = 20.dp

    /*
    Fully rounded: category chips, "Ingredients" filter button, "Add" button (ChipCategory,
    IngredientsButton, AddButton)
     */
    val pill = 999.dp
}