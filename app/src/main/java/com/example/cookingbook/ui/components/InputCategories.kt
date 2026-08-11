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

val categories = listOf(
    DropdownItem("Tout"),
    DropdownItem("Entrées"),
    DropdownItem("Plats"),
    DropdownItem("Desserts"),
    DropdownItem("Pains"),
    DropdownItem("Boissons"),
    DropdownItem("A tester"),
    DropdownItem("Pas chères et faciles")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCategories(modifier: Modifier = Modifier){
    var expanded by remember{
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf(DropdownItem("Desserts"))
    }
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
                value = selectedItem.title,
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
                            selectedItem = categories[index]
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