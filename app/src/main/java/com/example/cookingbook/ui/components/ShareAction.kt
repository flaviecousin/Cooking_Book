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

enum class ShareAction {SHARE, OPEN}

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