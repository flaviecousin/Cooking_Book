package com.example.cookingbook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * What to do with a recipe export once it's been generated: [SHARE] via the system share sheet, or
 * [OPEN] directly via a matching app.
 */
enum class ShareAction {SHARE, OPEN}

/**
 * Dialog letting the user choose an export format ([ShareFormat.IMAGE] or [ShareFormat.PDF]) before
 * sharing or opening a recipe export.
 *
 * Note the dialog's own confirm/dismiss buttons don't map to "confirm the choice" / "cancel" as their
 * positions might suggest: the **confirm** button triggers [ShareAction.SHARE], while the **dismiss**
 * button triggers [ShareAction.OPEN]. Both close the dialog and both invoke [onConfirm], just with
 * a different [ShareAction]. There's no actual cancel/dismiss-without-action path other than tapping
 * outside the dialog (which calls [onDismiss]).
 *
 * @param onDismiss invoked when the dialog is dismissed without a format action being chosen (tap
 * outside, back gesture).
 * @param onConfirm invoked with the selected [ShareFormat] and the chosen [ShareAction] once the user
 * picks "Partager" or "Ouvrir".
 */
@Composable
fun ShareFormatDialog(onDismiss: () -> Unit, onConfirm: (ShareFormat, ShareAction) -> Unit){
    var selectedFormat by remember { mutableStateOf(ShareFormat.IMAGE) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {Text("Partager la recette")},
        text = {
            Column {
                Text("Format :")
                Row {
                    TextButton(onClick = {selectedFormat = ShareFormat.IMAGE }) {
                        Text(if(selectedFormat == ShareFormat.IMAGE) "• Image" else "○ Image")
                    }
                    TextButton(onClick = {selectedFormat = ShareFormat.PDF }) {
                        Text(if(selectedFormat == ShareFormat.PDF) "• PDF" else "○ PDF")
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {onConfirm(selectedFormat, ShareAction.SHARE) }) {
                Text("Partager")
            }
        },
        dismissButton = {
            TextButton(onClick = {onConfirm(selectedFormat, ShareAction.OPEN) }) {
                Text("Ouvrir")
            }
        }
    )
}