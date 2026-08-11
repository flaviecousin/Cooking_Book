package com.example.cookingbook.ui.components

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookingbook.ui.icons.FluentuiSystemIconsSearch
import com.example.cookingbook.ui.models.FilterTextViewModel
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing
//import com.example.cookingbook.ui.icons.VscodeCodiconsError

@Composable
fun SearchBar(modifier: Modifier, viewModel: FilterTextViewModel = viewModel()){
    val filteredItems by viewModel.filteredItems.collectAsState()
    var text by rememberSaveable{ mutableStateOf("") }
    Column(modifier= Modifier
        .padding(Spacing.sm)
    ){
            OutlinedTextField(
                value=text,
                onValueChange = {
                    text = it
                    viewModel.filterText(text)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(Radius.md),
                placeholder = {
                    Text(text = "Rechercher une recette...",
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
                // A ajouter si envie : icône permettant de supprimer le texte de l'input
                /*trailingIcon = {
                    Icon(imageVector = VscodeCodiconsError ,contentDescription = "Supprimer la sélection")
                }*/
            )
        if (text.isNotEmpty()){
            LazyColumn{
                items(
                    count = filteredItems.size,
                    key = {index -> filteredItems[index]}
                ){
                    ListItem(
                        headlineContent = {Text(filteredItems[it])},
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(Spacing.sm)
                    )
                }
            }
        }
    }
}