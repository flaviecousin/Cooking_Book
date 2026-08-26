package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cookingbook.ui.data.Preparation
import com.example.cookingbook.ui.theme.Spacing

/**
 * Read-only display of a recipe's preparation steps, shown in the recipe detail screen (counterpart
 * to the editable [AddSteps] in 'AddStepsRecipe.kt').
 *
 * @param preparation the steps to display, rendered in list order (not re-sorted by [Preparation.numero],
 * so callers are expected to already pass them in the intended order).
 */
@Composable
fun StepsList(preparation: List<Preparation>){
    Column(modifier = Modifier.padding(Spacing.sm)){
        preparation.forEach {step ->
            EachStep(
                number = step.numero,
                step = step.etape
            )
            Spacer(Modifier.height(Spacing.md))
        }
    }
}

/**
 * A single read-only step row: a numbered circular badge followed by the instruction text.
 *
 * @param number the step's display number, shown inside the badge.
 * @param step the step's instruction text.
 */
@Composable
fun EachStep(number: Int, step: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(Spacing.xs),
        verticalAlignment = Alignment.CenterVertically) {
        Card(
            modifier = Modifier.size(Spacing.xxl),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background
            )
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number.toString(),
                    style= MaterialTheme.typography.bodySmall,
                )
            }
        }
        Spacer(Modifier.width(Spacing.md))
        Text(
            text = step,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}