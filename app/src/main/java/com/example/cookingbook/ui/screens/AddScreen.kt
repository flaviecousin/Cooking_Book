package com.example.cookingbook.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.components.AddIngredients
import com.example.cookingbook.ui.components.AddSteps
import com.example.cookingbook.ui.components.InputCategories
import com.example.cookingbook.ui.components.InputTexte
import com.example.cookingbook.ui.components.InputTime
import com.example.cookingbook.ui.components.NumberPeopleInput
import com.example.cookingbook.ui.components.SavingButton
import com.example.cookingbook.ui.components.WidgetImg
import com.example.cookingbook.ui.data.Ingredient
import com.example.cookingbook.ui.data.Preparation
import com.example.cookingbook.ui.data.Recette
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.theme.Spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(viewModel: RecetteViewModel){
    var newRecipe by remember { mutableStateOf(recetteVide()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost =  {SnackbarHost(snackbarHostState)},
        topBar = {
            TopAppBar(
                expandedHeight = 65.dp,
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {TitleScreen()},
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                windowInsets = WindowInsets(0.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ){
                    SavingButton(onClick = {
                        viewModel.ajouterRecette(newRecipe){
                            newRecipe = recetteVide()
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("La recette a bien été enregistrée !")
                            }
                        }
                    })
                }
            }
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
                WidgetImg (value = newRecipe.image, onValueChange = {newRecipe = newRecipe.copy(image = it)})
                Spacer(Modifier.height(Spacing.lg))
                InputTexte("Titre","Ex : Tarte Tatin", value = newRecipe.titre, onValueChange = {newRecipe = newRecipe.copy(titre = it)})
                InputCategories(value = newRecipe.categorie, onValueChange = {newRecipe = newRecipe.copy(categorie = it)})
                NumberPeopleInput(value = newRecipe.people, onValueChange = {newRecipe = newRecipe.copy(people = it)})

                // Gestion des différents temps de la recette
                Text(
                    text = "Temps (en minutes)".uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(Spacing.md))
                Row{
                    InputTime("Préparation", value = newRecipe.tempsPreparation, onValueChange = {newRecipe = newRecipe.copy(tempsPreparation = it)})
                    InputTime("Cuisson",  value = newRecipe.tempsCuisson, onValueChange = {newRecipe = newRecipe.copy(tempsCuisson = it)})
                    InputTime("Repos",  value = newRecipe.tempsRepos, onValueChange = {newRecipe = newRecipe.copy(tempsRepos = it)})
                }
                Spacer(Modifier.height(Spacing.sm))

                AddIngredients(value = newRecipe.ingredients, onValueChange = {newRecipe = newRecipe.copy(ingredients = it)})
                AddSteps(value = newRecipe.instructions, onValueChange = {newRecipe = newRecipe.copy(instructions = it)})
                InputTexte("Conseils & Avis","Notes personnelles, astuces, idées d'accompagnement...", value = newRecipe.conseils, onValueChange = {newRecipe = newRecipe.copy(conseils = it)})
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

fun recetteVide() = Recette(
    image = "",
    titre = "",
    categorie = "Desserts",
    people = 4,
    tempsPreparation = 0,
    tempsCuisson = 0,
    tempsRepos = 0,
    ingredients = listOf(Ingredient(id = 1, ingredient = "", quantite = "")),
    instructions = listOf(Preparation(numero = 1, etape = "")),
    conseils = ""
)