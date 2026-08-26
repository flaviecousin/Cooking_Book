package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.data.Ingredient
import com.example.cookingbook.ui.icons.BootstrapDot
import com.example.cookingbook.ui.icons.HeroiconsPlus
import com.example.cookingbook.ui.icons.VscodeCodiconsError
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Small pill-shaped "Add" button, shared between the ingredients and preparation-steps sections of
 * the add/edit recipe form (see [AddIngredients] and 'AddSteps' in 'AddStepsRecipe.kt').
 *
 * @param onClick invoked when the button is tapped; the caller decides what new empty item to append.
 */
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
                contentDescription = "Icône plus",
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

/**
 * A single editable ingredient row: a quantity field and a name field side by side, with an optional
 * delete button. Purely presentational (all state lives in the caller ([AddIngredients])), which passes
 * down the current values and receives change/delete callbacks.
 *
 * @param labelNumber the row's 1-based position, shown only in the fields' placeholder text (e.g.
 * "Quantité n°2"), not a stable identifier (see [AddIngredients] for the actual identity key used).
 * @param buttonShown whether the delete icon button is rendered. Callers hide it when only one row
 * remains, so the list can never be emptied entirely.
 * @param onDelete invoked when the delete icon is tapped.
 * @param value the quantity field's current text.
 * @param onValueChange invoked with the new quantity text on every keystroke.
 * @param nameIngredient the ingredient name field's current text.
 * @param onNameChange invoked with the new ingredient name on every keystroke.
 */
@Composable
fun WidgetIngredient(labelNumber: Int, buttonShown : Boolean, onDelete: () -> Unit,
                     value: String, onValueChange: (String) -> Unit,
                     nameIngredient: String, onNameChange: (String) -> Unit){

    Column {
        Spacer(Modifier.height(Spacing.sm))
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = BootstrapDot, contentDescription = "Icône point", tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(Spacing.md))
            OutlinedTextField(
                value=value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(Radius.md),
                placeholder = {
                    Text(text = "Quantité n° $labelNumber",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            OutlinedTextField(
                value=nameIngredient,
                onValueChange = onNameChange,
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
            // Delete cross (icon button)
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

/**
 * Editable list of a recipe's ingredients, shown in the add/edit form.
 * Owns the "add" and "remove" logic; each row's own field edits are delegated to [WidgetIngredient].
 *
 * New ingredients are appended with an 'id' of 'max(existing ids) + 1' (falling back to '1' for an
 * empty list). ids are only ever unique within this single list, not globally, and are reused as the
 * stable Compose 'key' for each row (via 'key(ingredient.id)') so text field focus and cursor position
 * survive reordering/recomposition when other rows are added or removed.
 *
 * @param value the current ingredient list (owned by the parent from state).
 * @param onValueChange invoked with the full updated list on every add, edit or removal (this composable
 * holds no state of its own).
 */
@Composable
fun AddIngredients(value: List<Ingredient>, onValueChange: (List<Ingredient>) -> Unit){
    Column{
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ingrédients".uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            AddButton(onClick = {
                val newId = (value.maxOfOrNull { it.id } ?: 0)+1
                onValueChange(value + Ingredient(id = newId, ingredient = "", quantite = ""))
            })
        }
        value.forEachIndexed { index, ingredient ->
            key(ingredient.id){
                WidgetIngredient(
                    labelNumber = index + 1,
                    buttonShown = value.size > 1,
                    onDelete = {
                        onValueChange(value.filter { it.id != ingredient.id })
                    },
                    value = ingredient.quantite,
                    onValueChange = {newQuantite ->
                        onValueChange(value.map {
                            if (it.id == ingredient.id) it.copy(quantite = newQuantite) else it
                        })
                    },
                    nameIngredient = ingredient.ingredient,
                    onNameChange = { newName ->
                        onValueChange(value.map {
                            if (it.id == ingredient.id) it.copy(ingredient = newName) else it
                        })
                    }
                )
            }
        }
        Spacer(Modifier.height(Spacing.sm))
    }
}