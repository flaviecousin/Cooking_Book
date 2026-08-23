package com.example.cookingbook.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Spacing scale used throughout the app. Values are not a strict geometric scale ('xl' and 'xxxl'
 * were added ad hoc for specific layouts).
 */

object Spacing {
    /*
    Small icon-to-text gaps and outer padding on pills/chips
    (ChipCategory, IngredientButton, AddButton icon spacer)
     */
    val xs = 4.dp

    /*
    Most common internal padding: form field spacers, list item padding, card/pill padding
    (WidgetIngredient, WidgetSteps, AdviceCard, TotalTimeCard,IngredientList)
     */
    val sm = 8.dp

    /*
    Icon-to-text gaps in list tows (IngredientList, AddIngredient, AddStepsRecipe) and grid gutter
    (RecipeGridScreen's 'spacedBy')
     */
    val md = 12.dp

    /*
    Vertical gap between form sections (InputTexte, NumberPeopleInput, InputCategories) and
    SavingButton's vertical padding
     */
    val lg = 16.dp

    // Horizontal padding of the AddScreen's form content column
    val xl = 22.dp

    // Size of the circular step-number button/card (WidgetSteps, EachStep)
    val xxl = 32.dp

    //Base height contribution for the photo picker button (WidgetImg uses 'xxxl + 80.dp')
    val xxxl = 100.dp
}