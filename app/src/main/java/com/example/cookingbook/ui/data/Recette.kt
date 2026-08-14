package com.example.cookingbook.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recettes")
data class Recette(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titre: String,
    val ingredients : String,
    val instructions: String,
    val tempsPreparation: Int,
    val tempsCuisson: Int,
    val tempsRepos: Int,
    val image: String? = null
)
