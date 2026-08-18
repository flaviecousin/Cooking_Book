package com.example.cookingbook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.components.ChipCategory
import com.example.cookingbook.ui.components.IngredientsButton
import com.example.cookingbook.ui.components.IngredientsFilterWindow
import com.example.cookingbook.ui.components.RecipeCard
import com.example.cookingbook.ui.components.SavingButton
import com.example.cookingbook.ui.components.SearchBar
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.theme.Spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeGridScreen(viewModel: RecetteViewModel){
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            },
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {Title()}
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.size(0.dp)
            ) {}
        }
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            SearchBar(modifier = Modifier.fillMaxWidth())
            CategoryList()
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.surface
            )
            ListeRecettesScreen(viewModel)
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    var showIngredientsFilter by remember { mutableStateOf(false)}
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
            //verticalAlignment = Alignment.Bottom
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recettes",
                modifier = modifier,
                style= MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            IngredientsButton(onClick={showIngredientsFilter=true})
        }
        if (showIngredientsFilter){
            IngredientsFilterWindow(
                onDismiss = { showIngredientsFilter = false }
            )
        }
    }
}

@Composable
fun CategoryList(){
    var categorySelected by remember{ mutableStateOf("Tout") }
    val categories = listOf("Tout", "Entrées", "Plats", "Desserts", "Pains", "Boissons", "A tester","Pas chères et faciles")
    Row(modifier = Modifier.horizontalScroll(rememberScrollState()))
    {
        categories.forEach { categorie ->
            ChipCategory(
                texte = categorie,
                isSelected = categorie == categorySelected,
                onClick = {categorySelected = categorie}

            )
        }
    }
}

@Composable
fun ListeRecettesScreen(viewModel: RecetteViewModel){
    val recettes by viewModel.recettes.collectAsState()
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
        contentPadding = PaddingValues(Spacing.md))
        {
        items(recettes){recette ->
            RecipeCard(titre = recette.titre,
                categorie = recette.categorie,
                tempsPrep = recette.tempsPreparation,
                tempsCuisson = recette.tempsCuisson,
                tempsRepos = recette.tempsRepos,
                nbPers = recette.people,
                img = recette.image,
                onClick = {
                    // A faire
                }
            )
            Spacer(modifier = Modifier.width(Spacing.md))
        }
    }
}