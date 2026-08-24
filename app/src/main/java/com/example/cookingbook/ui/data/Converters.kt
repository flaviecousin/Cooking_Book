package com.example.cookingbook.ui.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Room [TypeConverter]s that let [Recette.ingredients] and [Recette.instructions] (both plain Kotlin
 * lists, which Room can't persist natively) be stored as JSON text columns instead of a requiring a
 * separate relational table with foreign keys.
 *
 * Trade-off: this keeps the schema simple (a single 'recettes' table) at the cost of not being able
 * to query or index individual ingredients/steps at the SQL lever (e.g. the ingredient-based filter
 * in 'RecipeGridScreen.kt' has to load full recipes into memory and filter in Kotlin, rather than
 * filtering via SQL).
 */
class Converters {
    private val gson = Gson()

    /** Serialized an ingredient list to JSON for storage. */
    @TypeConverter
    fun fromIngredientList(ingredients: List<Ingredient>): String = gson.toJson(ingredients)

    /** Deserializes a stored JSON string back into a list of [Ingredient]. */
    @TypeConverter
    fun toIngredientList(json: String): List<Ingredient> =
        gson.fromJson(json, object : TypeToken<List<Ingredient>>() {}.type)

    /** Serializes a preparation step list to JSON for storage. */
    @TypeConverter
    fun fromPreparationList(preparations: List<Preparation>): String = gson.toJson(preparations)

    /** Deserializes a stored JSON string back into a list of [Preparation]. */
    @TypeConverter
    fun toPreparationList(json: String): List<Preparation> =
        gson.fromJson(json, object : TypeToken<List<Preparation>>() {}.type)
}