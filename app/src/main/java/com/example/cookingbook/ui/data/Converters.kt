package com.example.cookingbook.ui.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredientList(ingredients: List<Ingredient>): String = gson.toJson(ingredients)

    @TypeConverter
    fun toIngredientList(json: String): List<Ingredient> =
        gson.fromJson(json, object : TypeToken<List<Ingredient>>() {}.type)

    @TypeConverter
    fun fromPreparationList(preparations: List<Preparation>): String = gson.toJson(preparations)

    @TypeConverter
    fun toPreparationList(json: String): List<Preparation> =
        gson.fromJson(json, object : TypeToken<List<Preparation>>() {}.type)
}