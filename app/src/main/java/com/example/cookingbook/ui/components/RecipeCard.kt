package com.example.cookingbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookingbook.ui.icons.BootstrapDot
import com.example.cookingbook.ui.icons.LucideClock
import com.example.cookingbook.ui.icons.RadixPeople
import com.example.cookingbook.ui.theme.BrownCream
import com.example.cookingbook.ui.theme.Purpley
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.RosyPowdered
import com.example.cookingbook.ui.theme.Spacing

/**
 * Single recipe card shown in the 2-column recipe grid ([com.example.cookingbook.ui.screens.ListeRecettesScreen]).
 * Displays the photo edge-to-edge with a bottom gradient scrim, plus category, title, total time, and
 * servings overlaid at the bottom.
 *
 * If [img] is empty, the card simply renders as a flat [Purpley]-colored surface with no photo and
 * no gradient. There is currently no dedicated placeholder illustration for recipes without a photo.
 *
 * @param titre recipe title; truncated to 2 lines with an ellipsis if too long.
 * @param categorie recipe's category, shown uppercased above the title.
 * @param tempsPrep preparation time in minutes, summed with the other 2 to compute the displayed total.
 * @param tempsCuisson cooking time in minutes.
 * @param tempsRepos resting time in minutes.
 * @param nbPers number of servings.
 * @param img path/URI to the recipe photo, or an empty string to render without one.
 * @param onClick invoked when the card is tapped, typically to navigate to the recipe's detail screen.
 */
@Composable
fun RecipeCard (titre: String, categorie: String, tempsPrep: Int, tempsCuisson: Int, tempsRepos:Int, nbPers: Int, img: String, onClick: () -> Unit){
    val aUneImage = img.isNotEmpty()
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(Spacing.xs)
            .fillMaxWidth()
            .height(200.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Purpley
        ),
        shape = RoundedCornerShape(Radius.lg),
        contentPadding = PaddingValues(0.dp)
    ){
        Box(modifier = Modifier.fillMaxSize()){
            if (aUneImage){
                AsyncImage(
                    model = img,
                    contentDescription = "Photo de $titre",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Bottom gradient scrim so the overlaid text stays legible over the photo
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent,Purpley),
                                startY = 200f
                            )
                        )
                )
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.md),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ){
                Text(
                    text = categorie.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = BrownCream
                )
                Text(
                    text = titre,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(Spacing.xs))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = LucideClock, contentDescription = "Icône de temps", modifier = Modifier.size(13.dp), tint = BrownCream)
                    Spacer(modifier = Modifier.width(5.dp))
                    CalculTime(tempsCuisson + tempsPrep + tempsRepos)
                    Icon(imageVector = BootstrapDot, contentDescription = "Point", modifier = Modifier.size(12.dp))
                    // Displays the number of servings
                    Icon(imageVector = RadixPeople, contentDescription = "Nombre de personnes", modifier = Modifier.size(13.dp), tint = BrownCream)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "$nbPers",
                        style = MaterialTheme.typography.labelSmall,
                        color = RosyPowdered
                    )
                }
            }
        }
    }
}

@Composable
fun CalculTime (tempsTot: Int){
    if (tempsTot >= 60){
        val heure = tempsTot/60
        val minutes = tempsTot - (heure * 60)
        if (minutes<10){
            val minutesZeroPadded = "0$minutes"
            Text(
                text = heure.toString() + "h " + minutesZeroPadded + " min",
                style = MaterialTheme.typography.labelSmall,
                color = RosyPowdered
            )
        }
        else {
            Text(
                text = heure.toString() + "h " + minutes.toString() + " min",
                style = MaterialTheme.typography.labelSmall,
                color = RosyPowdered
            )
        }
    }
    else {
        Text(
            text = "$tempsTot min",
            style = MaterialTheme.typography.labelSmall,
            color = RosyPowdered
        )
    }
}