package com.example.cookingbook.ui.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cookingbook.ui.icons.HeroiconsBookOpen
import com.example.cookingbook.ui.icons.FluentuiSystemIconsAddCircle

/**
 * Top-level navigation destinations shown in the app's bottom
 * [com.example.cookingbook.ui.components.NavigationBar], each pairing a route (used by
 * [androidx.navigation.NavHostController]) with the label and icon rendered for that tab.
 *
 * Notably absent from this enum: the recipe detail screen ('recipe_detail/{recipeId}') and the edit
 * flow, both reached via 'navController.navigate(...)' calls with a dynamic route rather than through
 * a bottom nav tab (this enum only covers the two always-visible top-level destinations).
 */
enum class Destination (
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
){
    /** The recipe grid/browsing screen (the app's home tab). */
    RECETTES("recettes","Recettes", HeroiconsBookOpen, "Recettes"),

    /** The add-recipe form, in its create-mode entry point. */
    AJOUTER("ajouter","Ajouter", FluentuiSystemIconsAddCircle, "Add")
}
