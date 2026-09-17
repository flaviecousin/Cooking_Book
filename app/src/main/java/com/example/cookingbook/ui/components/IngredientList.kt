package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.data.Ingredient
import com.example.cookingbook.ui.icons.BootstrapDot
import com.example.cookingbook.ui.theme.Spacing

/**
 * Read-only display of a recipe's ingredient list, shown in the recipe detail screen (as opposed to
 * [AddIngredients], its editable counterpart in the add/edit form).
 *
 * @param ingredients the ingredients to display, rendered in list order.
 */
@Composable
fun IngredientsList(ingredients: List<Ingredient>){
    Column(modifier = Modifier.padding(Spacing.sm)){
        ingredients.forEach {ingredient ->
            LineIngredient(
                quantite = ingredient.quantite,
                nourriture = ingredient.ingredient
            )
        }
    }
}

/**
 * A single read-only ingredient row: a dot marker, the combined quantity + ingredient text, and a
 * hairline divider underneath.
 *
 * The combined text has 3 cases, evaluated in order:
 * 1. If [quantite] is blank, only [nourriture] is shown, with no leading space (avoids a stray leading
 * space for ingredients recorded with no quantity at all).
 * 2. If [quantite] already ends in a space, [quantite] and [nourriture] are concatenated directly
 * with no extra space inserted. This lets a recipe author work around awkward spacing for quantities
 * that don't read naturally with a space before the ingredient name (e.g. a quantity of "Un peu de
 * farine" rather than "Un peu de  farine" with a double space).
 * 3. Otherwise, [quantite] and [nourriture] are joined with a single space (e.g. "200f de" + " " +
 * "farine" -> "200g de farine").
 *
 * @param quantite the ingredient's quantity as free text, or blank if none was saved.
 * @param nourriture the ingredient's name.
 */
@Composable
fun LineIngredient(quantite: String, nourriture: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(Spacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(imageVector = BootstrapDot, contentDescription = "Icône point", tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(Spacing.md))
        val texteAffiche = when{
            quantite.isBlank() -> nourriture
            quantite.last() == ' ' -> quantite + nourriture
            else -> "$quantite $nourriture"
        }
        Text(
            text = texteAffiche,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    HorizontalDivider(thickness = (0.5).dp, color = MaterialTheme.colorScheme.surface)
}