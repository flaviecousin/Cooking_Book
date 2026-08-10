package com.example.cookingbook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import com.example.cookingbook.R

// Set of Material typography styles to start with
val PlayfairDisplay = FontFamily(
    Font(
        R.font.playfair_display_bold,
        FontWeight.Bold,
        FontStyle.Normal
    ),
    Font(
        R.font.playfair_display_bold_italic,
        FontWeight.Bold,
        FontStyle.Italic
    ),
    Font(
        R.font.playfair_display_italic,
        FontWeight.Normal,
        FontStyle.Italic
    ),
    Font(
        R.font.playfair_display_regular,
        FontWeight.Normal,
        FontStyle.Normal
    )
)

val DSETypewriter = FontFamily(
    Font(
        R.font.dse_typewriter,
        FontWeight.Normal,
        FontStyle.Normal
    )
)
val Typography = Typography(
    // Display2XL
    displayLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 34.sp
    ),
    // DisplayLG : titre recette (détails) + "Ajouter une recette"
    displayMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 28.sp
    ),
    //h3 : titre de carte dans la grille
    titleLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    // label
    labelLarge = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.W600,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.4.sp
        // uppercase
    ),
    // body : texte des ingrédients, valeurs des champs
    bodyLarge = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    // body : texte des ingrédients, valeurs des champs
    bodySmall = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    // bodyMuted : placeholders, "Depuis la galerie..."
    bodyMedium = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = GreyPink
    ),
    // caption : métadonnées cartes (temps, personne)
    labelMedium = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = GreyPink
    )
)