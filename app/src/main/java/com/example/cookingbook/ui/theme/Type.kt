package com.example.cookingbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cookingbook.R

/**
 Serif family used for all display/title styles below (recipe titles, section headers).
 */
val PlayfairDisplay = FontFamily(
    Font(resId = R.font.playfair_display_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(resId = R.font.playfair_display_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.playfair_display_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.playfair_display_regular, weight = FontWeight.Normal, style = FontStyle.Normal)
)

/**
Monospace-style family used for body text, labels, and form values.
 */
val DSETypewriter = FontFamily(
    Font(resId = R.font.dse_typewriter, weight = FontWeight.Normal, style = FontStyle.Normal)
)

/**
 * App-wide [Typography]. Comments below describe actual usage found in the shared code.
 */
val Typography = Typography(
    // Big page/recipe titles: "Recettes" (grid screen), recipe title on the hero image
    displayLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 34.sp
    ),

    // Defined with a 90sp line height; no confirmed usage in the shared code (possibly leftover from an earlier iteration)
    displaySmall = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 90.sp
    ),

    // Subheadings: "ajouter une recette", "Mon Carnet de", "Pour X personnes", "Etape par étape"
    displayMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 28.sp
    ),

    //Recipe card title in the grid (RecipeCard.kt)
    titleLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    /* Uppercase section labels/eyebrows: used very broadly (chips, field, labels,
    "Ingrédients"/"Préparation" headers, card category text, NumberCard labels, "Nouveau" eyebrow)
     */
    labelLarge = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.6.sp
    ),

    // Advice/tips paragraph text (AdviceCard)
    bodyLarge = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),

    /* Despite the name, mostly used as a button/label: chip text, SavingButton, IngredientsButton,
    step-number and time-value digits (WidgetSteps, NumberCard, EachStep)
     */
    bodySmall = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),

    // Text field placeholders app-wide, and WidgetImg's helper caption
    bodyMedium = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = GreyPink
    ),

    // Small field labels above time inputs ("Préparation"/"Cuisson"/"Repos")
    labelMedium = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = GreyPink
    ),

    // Default color is overridden where used on dark card backgrounds (RecipeCard's time/servings text)
    labelSmall = TextStyle(
        fontFamily = DSETypewriter,
        fontSynthesis = FontSynthesis.Weight,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        color = RaspberryPink
    )
)