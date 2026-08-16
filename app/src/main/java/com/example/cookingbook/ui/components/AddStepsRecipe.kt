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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.icons.VscodeCodiconsError
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun WidgetSteps(labelNumber: Int, buttonShown : Boolean, onDelete: () -> Unit, onClick: () -> Unit){
    var text by rememberSaveable{ mutableStateOf("") }

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
                value=text,
                onValueChange = { text=it },
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
                )
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
fun AddSteps(){
    var nextId by remember { mutableIntStateOf(1) }
    var stepsId by remember { mutableStateOf(listOf(0)) }

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
                stepsId = stepsId + nextId
                nextId++
            })
        }
        stepsId.forEachIndexed { index, id ->
            key(id){
                WidgetSteps(
                    labelNumber = index + 1,
                    buttonShown = stepsId.size > 1,
                    onDelete = {
                        stepsId = stepsId.filter { it != id }
                    },
                    onClick = {}
                )
            }
        }
        Spacer(Modifier.height(Spacing.sm))
    }
}