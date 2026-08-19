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