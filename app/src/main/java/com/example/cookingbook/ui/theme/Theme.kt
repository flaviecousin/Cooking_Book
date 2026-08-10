package com.example.cookingbook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CookingBookColorScheme = lightColorScheme(
    // Famille rose
    primary = RaspberryPink,
    onPrimary = WarmCream,
    onSurfaceVariant = GreyPink,
    primaryContainer = RosyPowdered,
    onPrimaryContainer = RaspberryPink,
    // Famille crème/neutre
    surface = BrownCream,
    onSurface = GreyPink,
    surfaceVariant = GreyCream,
    background = WarmCream,
    //Famille violet :
    secondary = DarkPurple,
    onSecondary = WarmCream,
    onBackground = DeepAubergine
)

@Composable
fun CookingBookTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CookingBookColorScheme,
        typography = Typography,
        content = content
    )
}