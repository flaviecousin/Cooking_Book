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