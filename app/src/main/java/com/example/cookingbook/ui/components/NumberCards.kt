package com.example.cookingbook.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.RaspberryPink
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun NumberCard(text: String, icone: ImageVector, textIcone: String, number: Int){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = Spacing.sm)
            .height(40.dp)
            .width(80.dp),
        shape = RoundedCornerShape(Radius.sm),
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(all = 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = icone, contentDescription = textIcone, modifier = Modifier.size(12.dp), tint = RaspberryPink)
            Spacer(modifier = Modifier.width(Spacing.xs))
            Column{
                Text(
                    text = text.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (text != "Pers."){
                    Text(text = number.toString() + " min",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                else{
                    Text(text = number.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }
    }
}