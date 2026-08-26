package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Generic labeled single-line text field, reused across the add/edit recipe form for both the recipe
 * title and the "Conseils & Avis" notes fiels (see 'AddScreen.kt'). Purely controlled: holds no state
 * of its own beyond the text field's built-in editing behavior.
 *
 * @param texte the field's section label, shown uppercased above the input.
 * @param labelTexte placeholder text shown when [value] is empty.
 * @param value the field's current text.
 * @param onValueChange invoked with the new text on every keystroke.
 */
@Composable
fun InputTexte(texte: String, labelTexte: String, value: String, onValueChange: (String) -> Unit){
    Column{
        Text(
            text = texte.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(Spacing.md))
        OutlinedTextField(
            value=value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Radius.md),
            placeholder = {
                Text(text = labelTexte,
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
        Spacer(Modifier.height(Spacing.lg))
    }
}