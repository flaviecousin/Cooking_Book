package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.cookingbook.ui.icons.VscodeCodiconsCheck
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Primary save action for the add/edit recipe form, pinned in [androidx.compose.material3.BottomAppBar]
 * by 'AddScreen.kt'.
 *
 * The button's label always reads "Enregistrer la recette" regardless of whether the form is creating
 * or editing. The create/edit distinction is only reflected elsewhere (the top bar title and the
 * confirmation snackbar text in 'AddScreen.kt'), not on this button itself.
 *
 * @param onClick invoked when tapped; the caller decides whether this triggers a create or an update
 * based on its own edit-mode state.
 */
@Composable
fun SavingButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = Spacing.xs, vertical = Spacing.lg)
            .clip(RoundedCornerShape(Radius.md)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor= MaterialTheme.colorScheme.onPrimary
        )
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Icon(imageVector = VscodeCodiconsCheck, contentDescription = "Icone check")
            Spacer(Modifier.width(Spacing.sm))
            Text(
                text = "Enregistrer la recette",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}