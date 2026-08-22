package com.example.cookingbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookingbook.ui.components.NavBar
import com.example.cookingbook.ui.data.AppDatabase
import com.example.cookingbook.ui.data.RecetteRepository
import com.example.cookingbook.ui.data.RecetteViewModelFactory
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.theme.CookingBookTheme

/**
 * Main entry point of the Cooking Book application.
 *
 * This single activity hosts the entire Jetpack Compose UI (single-activity architecture). It's responsible for:
 * - manually wiring the dependency chain (database -> repository -> factory -> ViewModel), since no dependency injection framework (Hilt/Kotlin) is used,
 * - enabling edge-to-edge display for an immersive layout,
 * - mounting the root Compose tree via [NavBar], which then handles navigation between the different screens (recipe grid, add screen, recipe detail).
 *
 * @see NavBar Root composable handling the navigation and the bottom bar.
 * @see RecetteViewModel ViewModel exposing recipe state to the UI.
 * @see AppDatabase Room database containing the 'recettes' table.
 */

class MainActivity : ComponentActivity() {
    /**
     * Initialize the activity, builds the data layer dependencies, then declares the app's Compose content.
     *
     * Dependency injection is done manually here:
     * 1. [AppDatabase.getDatabase] retrieves (or creates) the singleton Room database instance.
     * 2. [RecetteRepository] wraps the DAO and exposes data as [kotlinx.coroutines.flow.Flow].
     * 3. [RecetteViewModelFactory] injects the repository into the [RecetteViewModel], since the latter has no empty constructor.
     *
     * @param savedInstanceState previously saved state of the activity, 'null' on first launch
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- Manually building the dependency chain ---
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = RecetteRepository(database.recetteDao())
        val factory = RecetteViewModelFactory(repository)

        // Enables edge-to-edge
        enableEdgeToEdge()

        setContent {
            // ViewModel scoped to the activity, survives config changes
            val viewModel: RecetteViewModel = viewModel(factory = factory)


            CookingBookTheme {
                // Navigation root: bottom bar + NavHost
                NavBar(viewModel = viewModel)
            }
        }
    }
}