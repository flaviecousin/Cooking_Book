package com.example.cookingbook.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.components.InputTexte
import com.example.cookingbook.ui.theme.Spacing
import com.example.cookingbook.ui.components.InputCategories
import com.example.cookingbook.ui.components.InputTimes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                expandedHeight = 65.dp,
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {TitleScreen()},
            )
        }
    ) { innerPadding ->
        //modifier = Modifier.horizontalScroll(rememberScrollState())
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(Spacing.sm))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.surface
            )
            Column (modifier = Modifier.padding(horizontal = Spacing.xl)){
                Spacer(Modifier.height(Spacing.lg))
                // Rectangle pour récupérer la photo à partir de la galerie et/ou de l'appareil photo
                Text("Bloc photo")
                Spacer(Modifier.height(Spacing.lg))
                InputTexte("Titre","Ex : Tarte Tatin")
                InputCategories()
                // ESpace nombre de personnes
                InputTimes()
                // Espace Ingrédients
                // Espace Préparation
                InputTexte("Conseils & Avis","Notes personnelles, astuces, idées d'accompagnement...")
            }
        }
    }
}

@Composable
fun TitleScreen(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Nouveau".uppercase(),
            modifier = modifier,
            style= MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "Ajouter une recette",
            modifier = modifier,
            style= MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}