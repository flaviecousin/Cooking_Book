package com.example.cookingbook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookingbook.ui.data.Recette
import com.example.cookingbook.ui.icons.FluentuiSystemIconsSearch
import com.example.cookingbook.ui.models.FilterTextViewModel
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Recipe title search bar, shown at the top of the recipe grid. Combines a text field with an inline
 * results dropdown listing matching titles.
 *
 * The [LaunchedEffect] keyed on [recettes] re-runs [FilterTextViewModel.setRecipes] whenever the recipe
 * list changes (e.g. after an add/edit/delete), keeping the searchable pool current and immediately
 * reapplying ant active search query.
 *
 * Each filtered result carries its own [Recette] (in particular its unique 'id'), so tapping a result
 * invokes [onResultClick] directly with that exact recipe - no secondary "find by title" lookup is
 * performed, which means 2 recipes sharing the exact same title are no longer ambiguous: whichever
 * one the user actually tapped is the one opened.
 *
 * @param viewModel owns the search text -> results filtering logic; default to a fresh instance scoped
 * to this composable via 'viewModel()'.
 * @param recettes the full recipe list to search against.
 * @param onResultClick invoked with the tapped result's [Recette]
 */
@Composable
fun SearchBar(
    viewModel: FilterTextViewModel = viewModel(),
    recettes: List<Recette>,
    onResultClick: (Recette) -> Unit = {},
){
    LaunchedEffect(recettes) {
        viewModel.setRecipes(recettes)
    }

    val filteredItems by viewModel.filteredItems.collectAsState()
    var text by rememberSaveable{ mutableStateOf("") }

    Column(modifier= Modifier.padding(Spacing.sm)){
        OutlinedTextField(
            value=text,
            onValueChange = {
                text = it
                viewModel.filterText(text)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Radius.md),
            placeholder = {
                Text(
                    text = "Rechercher une recette...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            leadingIcon = {
                Icon(imageVector = FluentuiSystemIconsSearch, contentDescription = "Rechercher")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface
            )
        )
        if (text.isNotEmpty()){
            LazyColumn{
                items(
                    count = filteredItems.size,
                    key = {index -> filteredItems[index].id}
                ){index ->
                    val recetteTrouvee = filteredItems[index]
                    ListItem(
                        headlineContent = {Text(recetteTrouvee.titre)},
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(Spacing.sm)
                            .clickable {
                                onResultClick(recetteTrouvee)
                            }
                    )
                }
            }
        }
    }
}