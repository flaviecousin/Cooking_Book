package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.icons.BootstrapDot
import com.example.cookingbook.ui.icons.HeroiconsPlus
import com.example.cookingbook.ui.icons.VscodeCodiconsError
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun AddButton (onClick : () -> Unit){
    FilledTonalButton(
        onClick = onClick,
        shape = RoundedCornerShape(Radius.pill),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor= MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = HeroiconsPlus,
                contentDescription = "Icone plus",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.height(14.dp))
            Spacer(Modifier.width(Spacing.xs))
            Text(
                text = "Ajouter",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun WidgetIngredient(labelNumber: Int, buttonShown : Boolean, onDelete: () -> Unit){
    var text by rememberSaveable{ mutableStateOf("") }

    Column {
        Spacer(Modifier.height(Spacing.sm))
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = BootstrapDot, contentDescription = "Icone point", tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(Spacing.md))
            OutlinedTextField(
                value=text,
                onValueChange = { text=it },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(Radius.md),
                placeholder = {
                    Text(text = "Ingrédient $labelNumber",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface
                )
            )
            Spacer(Modifier.width(Spacing.md))
            // Croix pour supprimer (c'est un bouton icône)
            if(buttonShown){
                IconButton(
                    onClick = onDelete,
                ){
                    Icon(imageVector = VscodeCodiconsError ,contentDescription = "Supprimer la sélection", tint = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}
@Composable
fun AddIngredients(){
    var ingredientNumber by remember { mutableStateOf(listOf(1)) }

    Column{
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            //verticalAlignment = Alignment.Bottom
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ingrédients".uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            AddButton(onClick = {
                ingredientNumber = ingredientNumber + (ingredientNumber.size+1)
            })
        }
        ingredientNumber.forEachIndexed { index, labelNumber ->
            WidgetIngredient(
                labelNumber = index + 1,
                buttonShown = ingredientNumber.size > 1,
                onDelete = { ingredientNumber = ingredientNumber.filterIndexed { i, _ -> i != index }}
            )
        }
        Spacer(Modifier.height(Spacing.sm))
    }
}