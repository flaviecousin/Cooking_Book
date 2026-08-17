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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cookingbook.ui.data.copyImgToInternalStorage
import com.example.cookingbook.ui.icons.FeatherCamera
import com.example.cookingbook.ui.theme.Radius
import com.example.cookingbook.ui.theme.Spacing

@Composable
fun WidgetImg(value: String, onValueChange: (String) -> Unit){
    val context = LocalContext.current
    val displayUri: Uri? = if (value.isNotEmpty()) Uri.parse(value) else null
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null){
            val savedPath = copyImgToInternalStorage(context, uri)
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
                // Utilisation de Coil pour afficher l'image sélectionner
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