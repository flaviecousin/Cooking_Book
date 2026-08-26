package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.cookingbook.ui.icons.FeatherFilter
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Entry point for the ingredient filter, shown in the recipe grid's header (see 'Title' in 'RecipeGridScreen.kt').
 * Visually reflects filter state: it switches to the 'primaryContainer' color and appends a live count
 * (e.g. "Ingrédients (3)") as soon as at least one ingredient is selected, so the active filter is
 * visible without opening the sheet.
 *
 * @param selectedCount number of ingredients currently selected in the filter; '0' renders the button
 * in its neutral/inactive state.
 * @param onClick invoked when tapped, to open [IngredientsFilterWindow].
 */
@Composable
fun IngredientsButton(selectedCount: Int, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier.padding(Spacing.xs).clip(RoundedCornerShape(Radius.pill)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selectedCount > 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
            contentColor= if (selectedCount > 0) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground
        )
    ){
        Icon(imageVector = FeatherFilter, contentDescription = "Filtre des ingrédients")
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (selectedCount > 0) "Ingrédients ($selectedCount)" else "Ingrédients",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/**
 * Bottom sheet listing every ingredient currently in use across all recipes (as computed by
 * [com.example.cookingbook.ui.screens.RecipeGridScreen]), each with a checkbox to toggle it as an
 * active filter. Purely presentational/controlled: all selection state is owned by the caller.
 *
 * Show a dedicated empty-state message rather than an empty list when [ingredients] is empty (i.e.
 * no recipe has any ingredient recorded yet).
 *
 * @param ingredients the full set of filterable ingredient names, already deduplicated and normalized
 * by the caller.
 * @param selectedIngredients the subset of [ingredients] currently active as filters, used to render
 * each row's checkbox state.
 * @param onToggleIngredient invoked with an ingredient name when its checkbox is tapped, regardless
 * of whether that turns the filter on or off.
 * @param onDismiss invoked when the sheet is dismissed (swipe down, scrim tap, or system back).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsFilterWindow(
    ingredients: List<String>,
    selectedIngredients: Set<String>,
    onToggleIngredient: (String) -> Unit,
    onDismiss:() -> Unit
){
    val windowState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = windowState
    ) {
        Text(
            text = "Filtrer par ingrédients",
            modifier = Modifier.padding(Spacing.lg),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayMedium
        )
        if (ingredients.isEmpty()){
            Text(
                text = "Aucun ingrédient enregistré pour le moment",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        else{
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 380.dp)
                .padding(horizontal = Spacing.lg)
            ) {
                items(ingredients, key = {it}){ingredient ->
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedIngredients.contains(ingredient),
                            onCheckedChange = {onToggleIngredient(ingredient)},
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = ingredient,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
        Spacer(Modifier.padding(Spacing.lg))
    }
}