package com.example.cookingbook.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

/**
 * Color palette for the Cooking Book design system
 * Each color's comment reflects where it is actually consumed in the codebase (either via [Theme.kt]'s
 * color scheme mapping, or referenced directly by name in a composable).
 */

// --- App background (Theme.kt: 'background'); top/nav bar container colors ---
val WarmCream = Color(0xFFFDFBF7)

// --- Main text color (Theme.kt: 'onBackground'), used across most 'Text' composables ---
val DeepAubergine = Color(0xFF2E2130)

/*
Theme.kt: 'primary' / 'onPrimaryContainer'. Referenced directly in AdvicesCard.kt, NumberCards.kt,
TotalTimeCard.kt (icon tints), and as the default color of 'labelSmall' in Type.kt.
 */
val RaspberryPink = Color(0xFF9C3F53)

/*
Theme.kt: 'primaryContainer'. Also used directly as text color in RecipeCard.kt (time/servings pill
on the card image) and RecipeScreen.kt (category label on the hero image).
 */
val RosyPowdered = Color(0xFFF0D6DB)

// --- Theme.kt: 'secondary'. Used as the selected-chip and step-number-circle background. ---
val DarkPurple = Color(0xFF4A2E4B)

/*
Theme.kt: 'surface'. In practice the most-used neutral: text field container color (InputTexte,
AddIngredient, AddStepsRecipe, InputCategories, InputTimes) and divider color ('HorizontalDivider'
in AddScreen, IngredientList, RecipeScreen, NavBar).
 */
 val BrownCream = Color(0xFFF1ECE8)

/*
Theme.kt: 'onSurface' / 'onSurfaceVariant'. Also hardcoded directly as the text color of 'bodyMedium'
and 'labelMedium' in Type.kt
 */
val GreyPink = Color(0xFF8A7A80)

/*
Translucent dark overlay. Used both as the base container color of [com.example.cookingbook.ui.components.RecipeCard]
(visible before/behind the image) and as the gradient end color on card and hero images.
 */
val Purpley = Color(
    red=46.0f/255.0f,
    green=33.0f/255.0f,
    blue=48.0f/255.0f,
    alpha=0.75f,
    colorSpace = ColorSpaces.Srgb)