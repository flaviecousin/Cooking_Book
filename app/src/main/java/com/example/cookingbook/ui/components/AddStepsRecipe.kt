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

@Composable
fun WidgetSteps(labelNumber: Int, buttonShown : Boolean, onDelete: () -> Unit, onClick: () -> Unit,
                value: String, onValueChange: (String) -> Unit){

    Column {
        Spacer(Modifier.height(Spacing.sm))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top) {
            Button(
                onClick = onClick,
                modifier = Modifier.size(Spacing.xxl),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
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