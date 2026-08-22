package com.example.cookingbook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.components.ChipCategory
import com.example.cookingbook.ui.components.IngredientsButton
import com.example.cookingbook.ui.components.IngredientsFilterWindow
import com.example.cookingbook.ui.components.RecipeCard
import com.example.cookingbook.ui.components.SearchBar
import com.example.cookingbook.ui.data.Recette
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.theme.Spacing

private fun normalizeIngredientKey(name: String): String{
    val lower = name.trim().lowercase()
    return when{
        lower.length > 1 && (lower.endsWith("s") || lower.endsWith("x")) -> lower.dropLast(1)
        else -> lower
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeGridScreen(viewModel: RecetteViewModel, onRecipeClick: (Recette) -> Unit){
    var categorySelected by remember{ mutableStateOf("Tout") }
    var selectedIngredients by remember { mutableStateOf(setOf<String>()) }
    var showIngredientFilter by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val recettes by viewModel.recettes.collectAsState()

    // Liste dynamique et dédupliquée (insensible à la casse) de tous les ingrédients existants
    val availableIngredients = remember(recettes){
        recettes
            .flatMap { it.ingredients }
            .map { it.ingredient.trim() }
            .filter { it.isNotBlank() }
            .groupBy { normalizeIngredientKey(it) }
            .map { (_, variantes) -> variantes.minBy { it.length } }
            .sortedBy { it.lowercase() }
    }
    // Si un ingrédient sélectionné disparaît (recette modifiée/supprimée), on nettoit la sélection
    LaunchedEffect(availableIngredients) {
        val availableSet = availableIngredients.toSet()
        if (!availableSet.containsAll(selectedIngredients)){
            selectedIngredients = selectedIngredients.intersect(availableSet)
        }
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        }
    ){
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            title = {
                Title(
                    selectedIngredientsCount = selectedIngredients.size,
                    onFilterClick = {showIngredientFilter = true}
                )
            }
        )
        SearchBar(recettes = recettes, onResultClick = onRecipeClick)
        CategoryList(
            categorySelected = categorySelected,
            onCategorySelected = {categorySelected = it}
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.surface
        )
        Box(modifier = Modifier.weight(1f)){
            ListeRecettesScreen(
                viewModel = viewModel,
                categorySelected = categorySelected,
                selectedIngredients = selectedIngredients,
                onRecipeClick = onRecipeClick
            )
        }
    }
    if (showIngredientFilter){
        IngredientsFilterWindow(
            ingredients = availableIngredients,
            selectedIngredients = selectedIngredients,
            onToggleIngredient = { ingredient ->
                selectedIngredients = if (ingredient in selectedIngredients){
                    selectedIngredients - ingredient
                }
                else{
                    selectedIngredients + ingredient
                }
            },
            onDismiss = {showIngredientFilter = false}
        )
    }
}

@Composable
fun Title(selectedIngredientsCount: Int, onFilterClick: () -> Unit, modifier: Modifier = Modifier) {
    //var showIngredientsFilter by remember { mutableStateOf(false)}
    Column {
        Text(
            text = "Mon Carnet de".uppercase(),
            modifier = modifier,
            style= MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recettes",
                modifier = modifier,
                style= MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            IngredientsButton(selectedCount = selectedIngredientsCount, onClick = onFilterClick)
        }
    }
}

@Composable
fun CategoryList(categorySelected: String, onCategorySelected: (String) -> Unit){
    val categories = listOf("Tout", "Entrées", "Plats", "Desserts", "Pains", "Boissons", "A tester","Pas chères et faciles", "BBQ", "Noël/Festif")
    Row(modifier = Modifier.horizontalScroll(rememberScrollState()))
    {
        categories.forEach { categorie ->
            ChipCategory(
                texte = categorie,
                isSelected = categorie == categorySelected,
                onClick = {onCategorySelected (categorie)}

            )
        }
    }
}

@Composable
fun ListeRecettesScreen(
    viewModel: RecetteViewModel,
    categorySelected: String,
    selectedIngredients: Set<String>,
    onRecipeClick: (Recette) -> Unit
){
    val recettes by viewModel.recettes.collectAsState()
    val recettesFiltrees = recettes
        .filter { categorySelected == "Tout" || it.categorie == categorySelected}
        .filter { recette ->
            selectedIngredients.isEmpty() || selectedIngredients.all {selected ->
                recette.ingredients.any{
                    normalizeIngredientKey(it.ingredient) == normalizeIngredientKey(selected)
                }
            }
        }

    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
        contentPadding = PaddingValues(Spacing.md))
        {
        items(recettesFiltrees){recette ->
            RecipeCard(titre = recette.titre,
                categorie = recette.categorie,
                tempsPrep = recette.tempsPreparation,
                tempsCuisson = recette.tempsCuisson,
                tempsRepos = recette.tempsRepos,
                nbPers = recette.people,
                img = recette.image,
                onClick = { onRecipeClick(recette) }
            )
        }
    }
}