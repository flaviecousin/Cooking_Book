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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.data.Ingredient
import com.example.cookingbook.ui.icons.BootstrapDot
import com.example.cookingbook.ui.theme.Spacing

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

@Composable
fun LineIngredient(quantite: String, nourriture: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.xs)
    ) {
        Row(){
            Icon(imageVector = BootstrapDot, contentDescription = "Icone point", tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(Spacing.md))
            // Gestion de l'espace à la fin de la chaîne de caractères quantités
            if (quantite.lastOrNull() == ' '){
                Text(
                    text = quantite + nourriture,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            else{
                Text(
                    text = "$quantite $nourriture",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        HorizontalDivider(thickness = (0.5).dp, color = MaterialTheme.colorScheme.surface)
    }
}