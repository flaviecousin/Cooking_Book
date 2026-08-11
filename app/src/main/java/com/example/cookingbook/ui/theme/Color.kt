package com.example.cookingbook.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

// Colors of the theme
val WarmCream = Color(0xFFFDFBF7) // fond principal (écran, cartes claires)
val DeepAubergine = Color(0xFF2E2130) // texte principal
val RaspberryPink = Color(0xFF9C3F53) // CTA, chip actif texte, labels de section, icônes actives
val RosyPowdered = Color(0xFFF0D6DB) // chips fonds inactifs, placeholder photo
val DarkPurple = Color(0xFF4A2E4B) // chips (fond actif), accents secondaires

val GreyCream = Color(0xFFF4EFEA) // fonds des inputs, search bar

//val BrownCream = Color(0xFFE8DFD8)
 val BrownCream = Color(0xFFF1ECE8) // bordures, dividers, fonds des pills d'infos (préparation, cuisson, repos)

val GreyPink = Color(0xFF8A7A80) // texte secondaire (placeholder, sous-titres)

val Purpley = Color(
    red=46.0f/255.0f,
    green=33.0f/255.0f,
    blue=48.0f/255.0f,
    alpha=0.75f,
    colorSpace = ColorSpaces.Srgb) // dégradé sombre en bas des images de carte