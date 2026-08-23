package com.example.cookingbook.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookingbook.ui.components.AdviceCard
import com.example.cookingbook.ui.components.IngredientsList
import com.example.cookingbook.ui.components.NumberCard
import com.example.cookingbook.ui.components.ShareAction
import com.example.cookingbook.ui.components.ShareButton
import com.example.cookingbook.ui.components.ShareFormatDialog
import com.example.cookingbook.ui.components.StepsList
import com.example.cookingbook.ui.components.TotalTimeCard
import com.example.cookingbook.ui.components.openRecipeFile
import com.example.cookingbook.ui.components.shareRecipe
import com.example.cookingbook.ui.data.Ingredient
import com.example.cookingbook.ui.data.Preparation
import com.example.cookingbook.ui.icons.FeatherThermometer
import com.example.cookingbook.ui.icons.FluentuiSystemIconsArrowLeft
import com.example.cookingbook.ui.icons.FluentuiSystemIconsDelete
import com.example.cookingbook.ui.icons.LucideClock
import com.example.cookingbook.ui.icons.PhosphorMoon
import com.example.cookingbook.ui.icons.RadixPencil1
import com.example.cookingbook.ui.icons.RadixPeople
import com.example.cookingbook.ui.theme.Purpley
import com.example.cookingbook.ui.theme.RosyPowdered
import com.example.cookingbook.ui.theme.Spacing
import com.example.cookingbook.ui.utils.CapturableContent
import kotlinx.coroutines.launch

/**
 * Overrides layout to report 0 size to its parent while still measuring (and thus letting) its content
 * draw normally underneath. Used to place an off-screen copy of [RecipeContent] into the composition
 * (for [CapturableContent] to record it via its [androidx.compose.ui.graphics.layer.GraphicsLayer])
 * without t taking up visible space or affecting the visible layout.
 */
private fun Modifier.captureFullSize() = this.layout { measurable, constraints ->
    val placeable = measurable.measure(
        constraints.copy(minHeight = 0, maxHeight = Constraints.Infinity)
    )
    layout(0, 0){
        placeable.placeRelative(0, 0)
    }
}

/**
 * Full recipe detail content: hero image with title/category overlay, time and servings metadata,
 * ingredients, steps, and advice section.
 *
 * This composable is rendered twice by [RecipeScreen]: once normally (visible, scrollable), and once
 * off-screen wrapped in [CapturableContent] purely to produce a bitmap for sharing (so any change
 * here affects both the on-screen layout and the exported PNG/PDF share format).
 */
@Composable
private fun RecipeContent(
    titre: String, categorie: String,
    tempsPrep: Int, tempsCuisson: Int, tempsRepos:Int,
    nbPers: Int, img: String, aUneImage: Boolean,
    ingredient: List<Ingredient>, preparation: List<Preparation>,
    conseils: String
){
    // ------ IMAGE + CATEGORY + RECIPE TITLE ------
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(300.dp)
            .background(Purpley)
    ) {
        if (aUneImage) {
            AsyncImage(
                model = img,
                contentDescription = "Photo de $titre",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Purpley),
                            startY = 25f
                        )
                    )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.md),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = categorie.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = RosyPowdered
            )
            Text(
                text = titre,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }

    // ------ TIME + SERVINGS ------
    Row(modifier = Modifier.fillMaxWidth()) {
        NumberCard(
            text = "Prép.",
            icone = LucideClock,
            textIcone = "Temps de préparation",
            number = tempsPrep
        )
        NumberCard(
            text = "Cuisson",
            icone = FeatherThermometer,
            textIcone = "Temps de cuisson",
            number = tempsCuisson
        )
        NumberCard(
            text = "Repos",
            icone = PhosphorMoon,
            textIcone = "Temps de repos",
            number = tempsRepos
        )
        NumberCard(
            text = "Pers.",
            icone = RadixPeople,
            textIcone = "Nombre de personne",
            number = nbPers
        )
        Spacer(modifier = Modifier.height(Spacing.md))
    }
    TotalTimeCard(
        tempsPrep = tempsPrep,
        tempsCuisson = tempsCuisson,
        tempsRepos = tempsRepos
    )
    Spacer(modifier = Modifier.height(Spacing.sm))
    HorizontalDivider(
        thickness = 2.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.md))

    // ------ INGREDIENTS ------
    Text(
        text = "Ingrédients".uppercase(),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.sm))
    Text(
        text = "Pour $nbPers personnes",
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.md))
    IngredientsList(ingredient)
    Spacer(modifier = Modifier.height(Spacing.sm))
    HorizontalDivider(
        thickness = 2.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.md))

    // ------ PREPARATION ------
    Text(
        text = "Préparation".uppercase(),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.sm))
    Text(
        text = "Étape par étape",
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.sm))
    StepsList(preparation = preparation)
    Spacer(modifier = Modifier.height(Spacing.sm))
    HorizontalDivider(
        thickness = 2.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.padding(Spacing.sm)
    )

    // ------ ADVICES & NOTES ------
    Text(
        text = "Conseils & avis".uppercase(),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.padding(Spacing.sm)
    )
    Spacer(modifier = Modifier.height(Spacing.sm))
    AdviceCard(advices = conseils)
    Spacer(modifier = Modifier.height(Spacing.lg))
}

/**
 * Recipe detail screen: displays a single recipe in full, and provides edit, delete and share actions.
 *
 * - **Edit / Delete**: the top bar exposes a pencil icon (invokes [onModification] directly) and a
 * trash icon, which opens a confirmation [AlertDialog] before invoking [onDelete] (deletion itself
 * is not fired directly from the icon tap).
 * - **Share**: a floating [ShareButton] opens [ShareFormatDialog], letting the user pick PNG or PDF
 * and whether to share or open the file. The actual bitmap is produced from an invisible, off-screen
 * copy of [RecipeContent] (see [captureFullSize] and [CapturableContent]) rather than from the visible,
 * scrollable one (so the exported file always captures the recipe at full height regardless of
 * current scroll position).
 * - The system back gesture/button is intercepted via [BackHandler] and routed through [onBack],
 * same as the top bar's back arrow.
 *
 * @param titre recipe title.
 * @param categorie recipe category, shown uppercased on the hero image.
 * @param tempsPrep preparation time in minutes.
 * @param tempsCuisson cooking time in minutes.
 * @param tempsRepos resting time in minutes.
 * @param nbPers number of servings.
 * @param img path/URI to the recipe photo, or an empty string if none.
 * @param onBack invoked when the user navigates back (top bar arrow or system back).
 * @param onDelete invoked after the user confirms deletion in the dialog.
 * @param onModification invoked when the user taps the edit (pencil) icon.
 * @param ingredient the recipe's ingredient list.
 * @param preparation the recipe's ordered preparation steps.
 * @param conseils free-form notes/tips text for the recipe.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    titre: String, categorie: String,
    tempsPrep: Int, tempsCuisson: Int, tempsRepos:Int,
    nbPers: Int, img: String,
    onBack: () -> Unit, onDelete: () -> Unit, onModification: () -> Unit,
    ingredient: List<Ingredient>, preparation: List<Preparation>,
    conseils: String
){
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val aUneImage = img.isNotEmpty()
    val graphicsLayer = rememberGraphicsLayer()
    val scope = rememberCoroutineScope()
    var showFormatDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    BackHandler(onBack = onBack)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0,0,0,0),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = { /*Optional title*/ },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = FluentuiSystemIconsArrowLeft,
                            contentDescription = "Flèche retour",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onModification) {
                        Icon(
                            imageVector = RadixPencil1,
                            contentDescription = "Modifier la recette",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            imageVector = FluentuiSystemIconsDelete,
                            contentDescription = "Supprimer la recette",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ShareButton(onClick = { showFormatDialog = true })
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                RecipeContent(
                    titre = titre, categorie = categorie,
                    tempsPrep = tempsPrep, tempsCuisson = tempsCuisson, tempsRepos = tempsRepos,
                    nbPers = nbPers, img = img, aUneImage = aUneImage,
                    ingredient = ingredient, preparation = preparation, conseils = conseils
                )
            }
            // Invisible, full-height copy used only to produce the shareable bitmap
            Box(modifier = Modifier.captureFullSize()){
                CapturableContent(graphicsLayer = graphicsLayer) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        RecipeContent(titre = titre, categorie = categorie,
                            tempsPrep = tempsPrep, tempsCuisson = tempsCuisson, tempsRepos = tempsRepos,
                            nbPers = nbPers, img = img, aUneImage = aUneImage,
                            ingredient = ingredient, preparation = preparation, conseils = conseils
                        )
                    }
                }
            }
            if (showFormatDialog) {
                ShareFormatDialog(
                    onDismiss = { showFormatDialog = false },
                    onConfirm = { format, action ->
                        showFormatDialog = false
                        scope.launch {
                            val bitmap = graphicsLayer.toImageBitmap()
                            try{
                                when (action) {
                                    ShareAction.SHARE -> shareRecipe(context, bitmap, fileName = titre.replace(" ", "_"), format)
                                    ShareAction.OPEN -> openRecipeFile(context, bitmap, fileName = titre.replace(" ", "_"), format)
                                }
                            }
                            catch (e: Throwable){
                                android.widget.Toast.makeText(
                                    context,
                                    "Erreur : ${e.javaClass.simpleName} - ${e.message}",
                                android.widget.Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                )
            }
            if (showDeleteDialog){
                AlertDialog(
                    onDismissRequest = {showDeleteDialog = false},
                    title = { Text("Supprimer la recette ?") },
                    text = { Text("Cette action est irréversible. Voulez-vous vraiment supprimer \"$titre\" ?")},
                    confirmButton = {
                        TextButton(onClick = {
                            showDeleteDialog = false
                            onDelete()
                        }) {
                            Text("Supprimer")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {showDeleteDialog = false}) {
                            Text("Annuler")
                        }
                    }
                )
            }
        }
    }
}