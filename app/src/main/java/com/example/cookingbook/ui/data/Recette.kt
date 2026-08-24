package com.example.cookingbook.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a single recipe (the app's only persisted entity, stored in the 'recettes'
 * table).
 *
 * [ingredients] and [instructions] are ordinary kotlin lists rather than related tables; they're
 * persisted as JSON text columns via [Converters] (see that class for the trade-offs of this approach).
 *
 * @property id auto-generated primary key. '0' by default for a not-yet-persisted recipe (e.g. the
 * blank form state built by 'recetteVide()' in 'AddScreen.kt'); Room assigns the real id on insert.
 * @property image absolute path to the recipe's photo in internal storage (see [copyImageToInternalStorage]),
 * or an empty string if no photo was set.
 * @property titre the recipe's title.
 * @property categorie the recipe's category label (currently a free-form string matched against the
 * hardcoded lists in 'InputCategories.kt' and 'RecipeGridScreen.kt''s 'CategoryList', rather than an
 * enum or foreign key, so a typo or a category present in one list but not the other can silently create
 * an uncategorized-looking recipe).
 * @property people number of servings the recipe yields.
 * @property tempsPreparation preparation time in minutes.
 * @property tempsCuisson cooking time in minutes.
 * @property tempsRepos resting time in minutes.
 * @property ingredients the recipe's ingredient list. Defaults to a single blank [Ingredient] so a
 * freshly-constructed [Recette] always has at least one editable row in the add form.
 * @property instructions the recipe's ordered preparation steps. Defaults to a single blank [Preparation]
 * for the same reason as [ingredients].
 * @property conseils free-form notes, tips, or serving suggestions.
 */
@Entity(tableName = "recettes")
data class Recette(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val image: String,
    val titre: String,
    val categorie: String,
    val people: Int,
    val tempsPreparation: Int,
    val tempsCuisson: Int,
    val tempsRepos: Int,
    val ingredients: List<Ingredient> = listOf(Ingredient(
        id = 1,
        ingredient = "",
        quantite = ""
    )),
    val instructions: List <Preparation> = listOf(Preparation(
        numero = 1,
        etape = ""
    )),
    val conseils: String
)
