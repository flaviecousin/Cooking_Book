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

/**
 * Declares the app's full navigation graph and wires each route to its screen composable and the
 * [viewModel] callbacks it needs.
 *
 * Routes:
 * - [Destination.RECETTES.route]: recipe grid; tapping a card navigates to 'recipe_detail/{id}'.
 * - 'recipe_detail/{recipeId}': recipe detail screen. Looks up the matching [com.example.cookingbook.ui.data.Recette]
 * from the current in-memory recipe list rather than querying the database directly (see
 * [com.example.cookingbook.ui.data.RecetteRequests.getRecetteById], which is unused). Renders nothing
 * if no recipe with that id is found (e.g. it was just deleted). Wired delete to [RecetteViewModel.supprimerRecette]
 * followed by popping back, and edit to navigating into 'recipe_edit/{id}'.
 * - 'recipe_edit/{recipeId}': reuses [AddScreen] in edit mode by passing it the found
 * [com.example.cookingbook.ui.data.Recette]; 'onSave' pops back to the detail screen.
 * - [Destination.AJOUTER.route]: [AddScreen] in create mode (no existing recipe passed).
 *
 * @param navController controls the navigation stack; shared with [NavBar] so bottom nav taps and
 * in-screen navigation actions stay in sync.
 * @param startDestination the initial route shown on launch.
 * @param viewModel supplies recipe data and read/write operations to every screen in the graph.
 */
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
                        navController.navigate("recipe_edit/${recette.id}")
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
        composable("recipe_edit/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toLongOrNull()
            val recettes by viewModel.recettes.collectAsState()
            val recette = recettes.find{it.id == recipeId}
            AddScreen(
                viewModel = viewModel,
                recetteExistante = recette,
                onSave = {navController.popBackStack()}
            )
        }
        composable (Destination.AJOUTER.route){
            AddScreen(viewModel)
        }
    }
}

/**
 * App shell: bottom [NavigationBar] plus the [NavigationHost] it drives.
 * The single entry point mounted by [com.example.cookingbook.MainActivity].
 *
 * [selectedDestination] tracks which bottom tab is highlighted, saved across process death via
 * [rememberSaveable]. Note it's only updated when a bottom nav item is tapped directly. Navigating
 * via other means (e.g. a recipe card, or popping back from the detail/edit screens) does not resync
 * it, so the highlighted tab can, in principle, become out of sync with the actually visible screen
 * if navigation happens through routes outside the bottom bar.
 *
 * @param viewModel passed straight through to [NavigationHost].
 */
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