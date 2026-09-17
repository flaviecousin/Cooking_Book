package com.example.cookingbook.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cookingbook.ui.data.copyImageToInternalStorage
import com.example.cookingbook.ui.icons.FeatherCamera
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

/**
 * Photo picker button used in [com.example.cookingbook.ui.screens.AddScreen] to attach a photo to a
 * recipe. Doubles as both the empty-state prompt ("Add a photo") and the preview of the currently
 * selected image, all within the same clickable surface (tapping it at any time re-opens the system
 * photo picker to change the selection).
 *
 * On pick, the returned 'content://' URI is immediately copied into app internal storage via
 * [copyImageToInternalStorage] so it survives across app restarts (see that function's docs for why).
 * If the copy fails, [onValueChange] is simply never called and the previous [value] is kept (there's
 * no error feedback shown to the user in that case).
 *
 * @param value the currently stored image path (an internal-storage file path, not the original picker
 * URI), or an empty string if no photo has been set yet.
 * @param onValueChange invoked with the new internal-storage file path once a picked image has been
 * successfully copied.
 */
@Composable
fun WidgetImg(value: String, onValueChange: (String) -> Unit){
    val context = LocalContext.current
    val displayUri: Uri? = if (value.isNotEmpty()) value.toUri() else null

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null){
            val savedPath = copyImageToInternalStorage(context, uri)
            if (savedPath != null){
                onValueChange(savedPath)
            }
        }
    }
    Box(contentAlignment = Alignment.Center){
        Button(
            onClick = {
                launcher.launch(PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly // N'autorise que les images comme média
                ))
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = RoundedCornerShape(Radius.sm),
            modifier = Modifier.height((Spacing.xxxl)+80.dp).fillMaxWidth()
        ) {
            if (displayUri != null){
                // Coil handles loading/caching the selected image
                val painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(context)
                        .data(data = displayUri)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = "Photo de la recette",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            }
            else{
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = FeatherCamera,
                        contentDescription = "Photo",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Ajouter une photo",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(text = "Depuis la galerie ou l'appareil photo",
                        style = MaterialTheme.typography.bodyMedium,
                        //color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}