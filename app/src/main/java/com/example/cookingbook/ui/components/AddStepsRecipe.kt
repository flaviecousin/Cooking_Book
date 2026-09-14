package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.cookingbook.ui.data.Preparation
import com.example.cookingbook.ui.icons.VscodeCodiconsError
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * A single editable preparation step row: a numbered circular badge, a text field for the step's
 * instructions, and an optional delete button.
 *
 * Note the numbered badge is a [Button] with an [onClick] parameter, but [AddSteps] always passes
 * an empty lambda for it (the badge is effectively non-interactive display-only in current usage,
 * despite being built as a clickable component).
 *
 * @param labelNumber the step's 1-based display position, shown both on the badge and in the field's
 * placeholder text. Not a stable identifier (see [AddSteps] for the actual identity key used).
 * @param buttonShown whether the delete icon button is rendered. Hidden when only one step remains,
 * so the list can never be emptied entirely.
 * @param onDelete invoked when the delete icon is tapped.
 * @param onClick invoked when the numbered badge is tapped (currently unused by callers).
 * @param value the step's current instruction text.
 * @param onValueChange invoked with the new instruction text on every keystroke.
 */
@Composable
fun WidgetSteps(labelNumber: Int, buttonShown : Boolean, onDelete: () -> Unit, onClick: () -> Unit,
                value: String, onValueChange: (String) -> Unit){

    Column {
        Spacer(Modifier.height(Spacing.sm))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top) {
            Card(
                modifier = Modifier.size(Spacing.xxl),
                shape = CircleShape,
                //contentPadding = PaddingValues(0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ){
                Text(
                    text = labelNumber.toString(),
                    style= MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(Modifier.width(Spacing.md))
            OutlinedTextField(
                value=value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(Radius.md),
                placeholder = {
                    Text(text = "Etape $labelNumber",
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
            Spacer(Modifier.width(Spacing.md))
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
 * Editable list of a recipe's preparation steps, shown in the add/edit form. Mirrors [AddIngredients]'
 * structure exactly, but keyed on [Preparation.numero] instead of ingredient id.
 *
 * New steps are appended with a 'numero' of 'max(existing numero) + 1' (falling back to '1' for an
 * empty list). As with ingredients, this number is only unique within this single list and doubles
 * as the stable Compose 'key' for each row.
 *
 * @param value the current step list (owned by the parent form state).
 * @param onValueChange invoked with the full updated list on every add, edit or removal.
 */
@Composable
fun AddSteps(value: List<Preparation>, onValueChange: (List<Preparation>) -> Unit){

    Column{
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Préparation".uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            AddButton(onClick = {
                val newId = (value.maxOfOrNull { it.numero } ?: 0)+1
                onValueChange(value + Preparation(numero = newId, etape = ""))
            })
        }
        value.forEachIndexed { index, preparation ->
            key(preparation.numero){
                WidgetSteps(
                    labelNumber = index + 1,
                    buttonShown = value.size > 1,
                    onDelete = {
                        onValueChange(value.filter{ it.numero != preparation.numero })
                    },
                    value = preparation.etape,
                    onValueChange = { newStep ->
                        onValueChange(value.map{
                            if (it.numero == preparation.numero) it.copy(etape = newStep) else it
                        })
                    },
                    onClick = {}
                )
            }
        }
        Spacer(Modifier.height(Spacing.sm))
    }
}