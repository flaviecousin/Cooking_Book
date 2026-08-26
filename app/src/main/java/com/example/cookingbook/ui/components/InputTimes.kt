package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Single time-value input (minutes), used 3 times in the add/edit for (once each for preparation,
 * cooking, and resting time (see the 'Row' of [InputTime] calls in 'AddScreen.kt')).
 *
 * @param texte label shown above the field (e.g. "Préparation").
 * @param value the current time value in minutes.
 * @param onValueChange invoked with the parsed integer value on every keystroke. Non-numeric input
 * silently falls back to '0' rather than rejecting the keystroke or showing a validation error.
 */
@Composable
fun InputTime(texte : String, value: Int, onValueChange: (Int) -> Unit){
    Column{
        Text(
            text = texte,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(Spacing.sm))
        OutlinedTextField(
            value=value.toString(),
            onValueChange = { newText ->
                onValueChange(newText.toIntOrNull() ?: 0)
            },
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
    }
}