package com.example.cookingbook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.example.cookingbook.ui.components.ChipCategory
import com.example.cookingbook.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeGridScreen(){
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){
                focusManager.clearFocus()
            },
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {Title()}
            )
        }
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            SearchBar(modifier = Modifier.fillMaxWidth())
            CategoryList()
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
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
            Text(
                text = "Filtre Ingrédients",
                modifier = modifier
            )
        }
    }
}

@Composable
fun CategoryList(){
    var categorySelected by remember{ mutableStateOf("Tout") }
    val categories = listOf("Tout", "Entrées", "Plats", "Desserts", "Pains", "Boissons", "A tester","Pas cher et facile")
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