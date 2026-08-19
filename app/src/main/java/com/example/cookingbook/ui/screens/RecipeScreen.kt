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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookingbook.ui.components.NumberCard
import com.example.cookingbook.ui.components.TotalTimeCard
import com.example.cookingbook.ui.data.Ingredient
import com.example.cookingbook.ui.data.Preparation
import com.example.cookingbook.ui.icons.FeatherThermometer
import com.example.cookingbook.ui.icons.FluentuiSystemIconsArrowLeft
import com.example.cookingbook.ui.icons.FluentuiSystemIconsDelete
import com.example.cookingbook.ui.icons.LucideClock
import com.example.cookingbook.ui.icons.PhosphorMoon
import com.example.cookingbook.ui.icons.RadixPencil1
import com.example.cookingbook.ui.icons.RadixPeople
import com.example.cookingbook.ui.theme.BrownCream
import com.example.cookingbook.ui.theme.Purpley
import com.example.cookingbook.ui.theme.RosyPowdered
import com.example.cookingbook.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    titre: String, categorie: String,
    tempsPrep: Int, tempsCuisson: Int, tempsRepos:Int,
    nbPers: Int, img: String,
    onBack: () -> Unit, onDelete: () -> Unit, onModification: () -> Unit,
    ingredient: List<Ingredient>, preparation: List<Preparation>,
    conseils: String, onPartage: () -> Unit
){
    //val tempsTotal = tempsCuisson + tempsPrep + tempsRepos
    val aUneImage = img.isNotEmpty()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    BackHandler(onBack = onBack)

    Column(modifier = Modifier
        .fillMaxSize()
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        }
    ) {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            title= { /*Intitulé optionnel*/ },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(imageVector = FluentuiSystemIconsArrowLeft,
                        contentDescription = "Flèche retour",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            actions = {
                IconButton(onClick = onModification) {
                    Icon(imageVector = RadixPencil1,
                        contentDescription = "Modifier la recette",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = FluentuiSystemIconsDelete,
                        contentDescription = "Supprimer la recette",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ){
            Box(modifier = Modifier
                .fillMaxSize()
                .height(300.dp)
                .background(Purpley)
            ){
                if (aUneImage){
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
                                    colors = listOf(Color.Transparent,Purpley),
                                    startY = 25f
                                )
                            )
                    )
                }
                Column(modifier = Modifier
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
            Row(modifier = Modifier.fillMaxWidth()) {
                NumberCard(text = "Prép.", icone = LucideClock, textIcone = "Temps de préparation", number = tempsPrep)
                NumberCard(text = "Cuisson", icone = FeatherThermometer, textIcone = "Temps de cuisson", number = tempsCuisson)
                NumberCard(text = "Repos", icone = PhosphorMoon, textIcone = "Temps de repos", number = tempsRepos)
                NumberCard(text = "Pers.", icone = RadixPeople, textIcone = "Nombre de personne", number = nbPers)
                Spacer(modifier = Modifier.height(Spacing.md))
            }
            TotalTimeCard(tempsPrep = tempsPrep, tempsCuisson = tempsCuisson, tempsRepos = tempsRepos)
            Spacer(modifier = Modifier.height(Spacing.sm))
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surface)
        }
    }
}