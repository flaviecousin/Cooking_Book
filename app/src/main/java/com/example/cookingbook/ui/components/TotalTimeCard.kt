package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.icons.FeatherWatch
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.RaspberryPink
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun TotalTimeCard(tempsPrep: Int, tempsCuisson: Int, tempsRepos: Int){
    val total = tempsPrep + tempsCuisson + tempsRepos
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        //border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier
            .padding(Spacing.sm)
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(Radius.md),
    ) {
        Row (modifier = Modifier.padding(Spacing.sm),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = FeatherWatch, contentDescription = "Icône montre", tint = RaspberryPink)
            Spacer(modifier = Modifier.width(Spacing.sm))
            Text(
                text = "Temps total : ",
                style = MaterialTheme.typography.bodyLarge
            )
            if (total >= 60){
                val heure = total/60
                val minutes = total - (heure * 60)
                Text(
                    text = heure.toString() + "h" + minutes.toString() + "min",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            else {
                Text(
                    text = "$total min",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}