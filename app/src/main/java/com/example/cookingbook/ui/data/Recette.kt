package com.example.cookingbook.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
