package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cookingbook.ui.icons.HeroiconsPlus
import com.example.cookingbook.ui.icons.RadixMinus
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun NumberPeopleInput(value: Int, onValueChange: (Int) -> Unit){
    //var count by remember { mutableIntStateOf(4) }
    val minValue = 0
    val maxValue = 1000

    Column(modifier = Modifier.fillMaxWidth()){
        Text(
            text = "Nombre de personnes".uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(Spacing.md))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            Button(
                onClick = {
                    if (value > minValue) onValueChange(value-1)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                enabled = value > minValue
            ){
                Icon(imageVector = RadixMinus, contentDescription = "Icone minus", tint = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(Modifier.width(Spacing.lg))
            Text(
                text = "$value",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.width(Spacing.lg))
            Button(
                onClick = {
                    if (value < maxValue) onValueChange(value+1)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                enabled = value < maxValue
            ) {
                Icon(imageVector = HeroiconsPlus, contentDescription = "Icone plus", tint = MaterialTheme.colorScheme.onBackground)
            }
        }
        Spacer(Modifier.height(Spacing.lg))
    }
}