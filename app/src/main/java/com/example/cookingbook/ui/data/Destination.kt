package com.example.cookingbook.ui.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cookingbook.ui.icons.HeroiconsBookOpen
import com.example.cookingbook.ui.icons.FluentuiSystemIconsAddCircle

enum class Destination (
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
){
    RECETTES("recettes","Recettes", HeroiconsBookOpen, "Recettes"),
    AJOUTER("ajouter","Ajouter", FluentuiSystemIconsAddCircle, "Add")
}
