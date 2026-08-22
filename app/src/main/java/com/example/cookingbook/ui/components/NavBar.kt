package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookingbook.ui.data.Destination
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.screens.AddScreen
import com.example.cookingbook.ui.screens.RecipeGridScreen
import com.example.cookingbook.ui.screens.RecipeScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: Destination,
    viewModel: RecetteViewModel,
    modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ){
        composable (Destination.RECETTES.route){
            RecipeGridScreen(
                viewModel = viewModel,
                onRecipeClick = { recette ->
                    navController.navigate("recipe_detail/${recette.id}")
                }
            )
        }
        composable ("recipe_detail/{recipeId}"){ backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toLongOrNull()
            val recettes by viewModel.recettes.collectAsState()
            val recette = recettes.find { it.id == recipeId }
            if (recette != null){
                RecipeScreen(
                    titre = recette.titre,
                    categorie = recette.categorie,
                    tempsPrep = recette.tempsPreparation,
                    tempsCuisson = recette.tempsCuisson,
                    tempsRepos = recette.tempsRepos,
                    nbPers = recette.people,
                    img = recette.image,
                    onDelete = {
                        viewModel.supprimerRecette(recette){
                            navController.popBackStack()
                        }
                    },
                    onModification = {
                        // A faire
                    },
                    onBack = {
                        navController.popBackStack()
                    },
                    ingredient = recette.ingredients,
                    preparation = recette.instructions,
                    conseils = recette.conseils
                )
            }
        }
        composable (Destination.AJOUTER.route){
            AddScreen(viewModel)
        }
    }
}

@Composable
fun NavBar(viewModel: RecetteViewModel, modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val startDestination = Destination.RECETTES
    var selectedDestination by rememberSaveable{ mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Column{
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.surface
                )
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background,
                    windowInsets = NavigationBarDefaults.windowInsets
                ) {
                    val itemColors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                    Destination.entries.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestination == index,
                            onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestination = index
                            },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDescription
                                )
                            },
                            label = { Text(destination.label) },
                            colors = itemColors
                        )
                    }
                }
            }
        }
    ){contentPadding ->
        NavigationHost(navController, startDestination, viewModel, modifier = Modifier.padding(contentPadding))
    }

}