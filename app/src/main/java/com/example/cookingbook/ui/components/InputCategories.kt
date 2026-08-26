package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.cookingbook.ui.data.DropdownItem
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Category options offered in the add/edit recipe form's dropdown. Kept in sync by hand with
 * 'CategoryList''s hardcoded list in 'RecipeGridScreen.kt'. Both currently list the same 10 categories
 * (including "Tout" here as a selectable value, even though it's only meaningful as a grid filter,
 * not as an actual recipe category). Since neither list derives from the other, they can drift apart
 * again if one is edited without the other; extracting a single shared source of truth would remove
 * that risk.
 */
val categories = listOf(
    DropdownItem("Tout"),
    DropdownItem("Entrées"),
    DropdownItem("Plats"),
    DropdownItem("Desserts"),
    DropdownItem("Pains"),
    DropdownItem("Boissons"),
    DropdownItem("A tester"),
    DropdownItem("Pas chères et faciles"),
    DropdownItem("BBQ"),
    DropdownItem("Noël/Festif")
)

/**
 * Read-only dropdown field for picking a recipe's category, used in the add/edit form. Implemented
 * as an [ExposedDropdownMenuBox] wrapping a non-editable [TextField]. The field only displays the
 * current selection and opens the menu on tap; direct typing is disable ('readOnly = true', empty
 * 'onValueChange').
 *
 * @param value the currently selected category label.
 * @param onValueChange invoked with the newly selected category's title when an item from [categories]
 * is tapped.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCategories(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit){
    var expanded by remember{ mutableStateOf(false) }

    Column{
        Text(
            text = "Catégories".uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(Spacing.md))
        ExposedDropdownMenuBox(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Radius.md)),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
                    .fillMaxWidth(),
                value = value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            )
            ExposedDropdownMenu(
                containerColor = MaterialTheme.colorScheme.surface,
                expanded = expanded,
                onDismissRequest = { expanded = false },
                shape = RoundedCornerShape(Radius.md)
            ) {
                categories.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(
                            text=item.title,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface) },
                        onClick = {
                            onValueChange(item.title)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
        Spacer(Modifier.height(Spacing.lg))
    }
}