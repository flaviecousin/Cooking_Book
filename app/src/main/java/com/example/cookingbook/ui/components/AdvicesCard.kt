package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cookingbook.ui.icons.BootstrapBookmark
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.RaspberryPink
import com.example.cookingbook.ui.theme.Spacing
import androidx.compose.foundation.text.selection.SelectionContainer

/**
 * Card displaying a recipe's free-form notes/tips ([Recette.conseils]), shown in the "Conseils & avis"
 * section of the recipe detail screen.
 *
 * The text is wrapped in a [SelectionContainer] so users can select and copy it (e.g. to paste a tip
 * elsewhere), the only text in the recipe detail screen made explicitly selectable this way.
 *
 * @param advices the notes/tips text to display. An empty string still renders the card (with a
 * bookmark icon and no visible text), rather than hiding the section, callers wanting to hide an empty
 * advice section entirely would need to check [advices] before calling this.
 */
@Composable
fun AdviceCard(advices: String){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier
            .padding(Spacing.sm)
            .fillMaxWidth(),
        shape = RoundedCornerShape(Radius.md),
    ) {
        Row (modifier = Modifier.padding(Spacing.md)){
            Icon(imageVector = BootstrapBookmark, contentDescription = "Icône bookmark", tint = RaspberryPink)
            Spacer(modifier = Modifier.width(Spacing.md))
            SelectionContainer{
                Text(
                    text = advices,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}