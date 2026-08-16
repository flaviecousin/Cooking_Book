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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cookingbook.ui.icons.HeroiconsPlus
import com.example.cookingbook.ui.icons.RadixMinus
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun NumberPeopleInput(){
    var count by remember { mutableIntStateOf(4) }
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
                    if (count > minValue) count --
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                enabled = count > minValue
            ){
                Icon(imageVector = RadixMinus, contentDescription = "Icone minus", tint = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(Modifier.width(Spacing.lg))
            Text(
                text = "$count",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.width(Spacing.lg))
            Button(
                onClick = {
                    if (count < maxValue) count++
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                enabled = count < maxValue
            ) {
                Icon(imageVector = HeroiconsPlus, contentDescription = "Icone plus", tint = MaterialTheme.colorScheme.onBackground)
            }
        }
        Spacer(Modifier.height(Spacing.lg))
    }
}