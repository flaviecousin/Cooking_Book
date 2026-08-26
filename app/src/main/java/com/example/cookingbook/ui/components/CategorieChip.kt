package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Single selectable pill-shaped chip used to build the horizontally scrollable category filter row in
 * [com.example.cookingbook.ui.screens.CategoryList]. Purely stateless and controlled: selection state
 * is entirely driven by [isSelected], owned by the caller.
 *
 * @param texte the category label to display.
 * @param isSelected whether this chip represents the currently active category filter; toggles between
 * the 'secondary'/'onSecondary' color pair (selected) and 'primaryContainer'/'onPrimaryContainer'
 * (unselected).
 * @param onClick invoked when the chip is tapped, regardless of its current selection state. The caller
 * decides how to interpret repeated taps on an already-selected chip.
 */
@Composable
fun ChipCategory(texte : String, isSelected : Boolean, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier.padding(Spacing.xs).clip(RoundedCornerShape(Radius.pill)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.secondary
            }
            else{
                MaterialTheme.colorScheme.primaryContainer
            },
            contentColor= if(isSelected){
                MaterialTheme.colorScheme.onSecondary
            }
            else {
                MaterialTheme.colorScheme.onPrimaryContainer
            }
        )
    ){
        Text(
            text = texte,
            style= MaterialTheme.typography.bodySmall,
        )
    }
}