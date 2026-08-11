package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.cookingbook.ui.icons.FeatherFilter
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing
import androidx.compose.ui.unit.dp

@Composable
fun IngredientsButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier.padding(Spacing.xs).clip(RoundedCornerShape(Radius.pill)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor= MaterialTheme.colorScheme.onBackground
        )
    ){
        // Icône de filtre
        Icon(imageVector = FeatherFilter, contentDescription = "Filtre des ingrédients")
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "Ingrédients",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsFilterWindow(onDismiss:() -> Unit){
    val windowState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = windowState
    ) {
        Text(
            text = "Filtrer par ingrédients",
            modifier = Modifier.padding(Spacing.lg)
        )
    }
}