package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun InputTimes(){
    Column{
        Text(
            text = "Temps (en minutes)".uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(Spacing.md))
        Row{
            OrganisationTime("Préparation")
            OrganisationTime("Cuisson")
            OrganisationTime("Repos")
        }
        Spacer(Modifier.height(Spacing.lg))
    }
}

@Composable
fun OrganisationTime(texte : String){
    var text by rememberSaveable{ mutableStateOf("") }
    Column{
        Text(
            text = texte,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(Spacing.sm))
        OutlinedTextField(
            value=text,
            onValueChange = {it -> text=it },
            modifier = Modifier.width(Spacing.xxxl),
            shape = RoundedCornerShape(Radius.md),
            placeholder = {
                Text(text = "0",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(Spacing.xs))
    }
}