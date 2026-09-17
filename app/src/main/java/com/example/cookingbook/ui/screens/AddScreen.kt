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
import com.example.cookingbook.ui.data.deleteInternalImage
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.theme.Spacing
import kotlinx.coroutines.launch

/**
 * Add/edit recipe form. The same composable and the same local [newRecipe] form state serve both flows
 * (the screen behaves as an editor when [recetteExistante] is non-null, and as a blank creation form
 * otherwise).
 *
 * Behavioral difference between the 2 modes, both driven by [isEditMode]:
 * - **Create** ('recetteExistante == null'): saving calls [RecetteViewModel.ajouterRecette], resets
 * the form back to [recetteVide], shows a confirmation snackbar, and stays on this screen (no navigation)
 * so another recipe can be added right away.
 * - **Edit** ('recetteExistante != null'): saving calls [RecetteViewModel.modifierRecette], shows a
 * confirmation snackbar, and invokes [onSave] (used by the caller to navigate back to the recipe
 * detail screen). The form is **not** reset in this case, since the screen is expected to be left
 * immediately after.
 * snackbarHostState surfaces both form-validation messages and save/update failures reported by the
 * ViewModel's on Error callback.
 *
 * @param viewModel used to persist the new or edited recipe.
 * @param recetteExistante the recipe being edited, or 'null' to create a new one from scratch.
 * @param onSave invoked after a successful edit-mode save, typically to navigate back. Not called
 * after a create-mode save.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    viewModel: RecetteViewModel,
    recetteExistante: Recette? = null,
    onSave: () -> Unit = {}
){
    var newRecipe by remember { mutableStateOf(recetteExistante ?: recetteVide()) }
    val isEditMode = recetteExistante != null
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
                title = {TitleScreen(isEditMode = isEditMode)},
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
                        val isTitleValid = newRecipe.titre.isNotBlank()
                        val hasValidIngredients = newRecipe.ingredients.any{it.ingredient.isNotBlank()}
                        val hasValidSteps = newRecipe.instructions.any{ it.etape.isNotBlank() }
                        when{
                            !isTitleValid -> {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Veuillez saisir un titre pour la recette")
                                }
                            }
                            !hasValidIngredients -> {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Veuillez saisir au moins un ingrédient")
                                }
                            }
                            !hasValidSteps -> {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Veuillez saisir au moins une étape de la préparation")
                                }
                            }
                            else -> {
                                val cleanedRecipe = newRecipe.copy(
                                    ingredients = newRecipe.ingredients.filter { it.ingredient.isNotBlank()},
                                    instructions = newRecipe.instructions.filter { it.etape.isNotBlank()}
                                )
                                if (isEditMode){
                                    viewModel.modifierRecette(
                                        cleanedRecipe,
                                        onSuccess = {
                                            val oldImage = recetteExistante?.image
                                            if (!oldImage.isNullOrBlank() && oldImage != cleanedRecipe.image){
                                                deleteInternalImage(oldImage)
                                            }
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("La recette a bien été modifiée !")
                                            }
                                            onSave()
                                        },
                                        onError = {message ->
                                            coroutineScope.launch{ snackbarHostState.showSnackbar(message)}
                                        }
                                    )
                                }
                                else{
                                    viewModel.ajouterRecette(
                                        cleanedRecipe,
                                        onSuccess = {
                                            newRecipe = recetteVide()
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("La recette a bien été enregistrée !")
                                            }
                                        },
                                        onError = { message ->
                                            coroutineScope.launch { snackbarHostState.showSnackbar(message) }
                                        }
                                    )
                                }
                            }
                        }
                    })
                }
            }
        }
    ) { innerPadding ->
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

                // Time fields (prep / cook / rest)
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

/**
 * Top bar title for [AddScreen], swapping its copy between create and edit mode ("Nouveau" / "Ajouter
 * une recette" vs "Modifier" / "Modifier la recette").
 *
 * @param isEditMode whether the screen is currently editing an existing recipe rather than creating
 * a new one.
 */
@Composable
fun TitleScreen(modifier: Modifier = Modifier, isEditMode: Boolean = false) {
    Column {
        Text(
            text = (if (isEditMode) "Modifier" else "Nouveau").uppercase(),
            modifier = modifier,
            style= MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = if (isEditMode) "Modifier la recette" else "Ajouter une recette",
            modifier = modifier,
            style= MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

/**
 * Builds a blank [Recette] used as the initial/reset state of the add form: empty title/image/advice,
 * a default category of "Desserts", 4 servings, zeroed times, and a single empty ingredient/step
 * placeholder so the dynamic list widgets (see 'AddIngredient.kt', 'AddStepsRecipe.kt') always start
 * with one editable row.
 */
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