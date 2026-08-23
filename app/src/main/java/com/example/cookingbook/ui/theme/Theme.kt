package com.example.cookingbook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Maps the custom palette onto Material3 color roles. Most components read 'surface', 'primaryContainer',
 * 'onBackground' explicitly instead of relying on the full MMaterial role set.
 */
private val CookingBookColorScheme = lightColorScheme(
    // Pink family
    primary = RaspberryPink,
    onPrimary = WarmCream,
    primaryContainer = RosyPowdered,
    onPrimaryContainer = RaspberryPink,
    // Cream/neutral family
    surface = BrownCream,
    onSurface = GreyPink,
    background = WarmCream,
    // Purple family
    secondary = DarkPurple,
    onSecondary = WarmCream,
    onBackground = DeepAubergine
)

/**
 * Applies the Cooking Book [MaterialTheme] (colors + typography) to [content]. Wraps the app's root
 * composable in [com.example.cookingbook.MainActivity].
 */
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