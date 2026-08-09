package com.example.cookingbook.ui.theme

import android.app.Activity
import android.os.Build
import android.telephony.RadioAccessSpecifier
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
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