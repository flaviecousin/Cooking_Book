package com.example.cookingbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookingbook.ui.components.NavBar
import com.example.cookingbook.ui.data.AppDatabase
import com.example.cookingbook.ui.data.RecetteRepository
import com.example.cookingbook.ui.data.RecetteViewModelFactory
import com.example.cookingbook.ui.models.RecetteViewModel
import com.example.cookingbook.ui.theme.CookingBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = RecetteRepository(database.recetteDao())
        val factory = RecetteViewModelFactory(repository)
        enableEdgeToEdge()
        setContent {
            val viewModel: RecetteViewModel = viewModel(factory = factory)
            CookingBookTheme {
                NavBar(viewModel = viewModel)
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Carnet de Recette de $name!",
        modifier = modifier,
        style= MaterialTheme.typography.displayLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CookingBookTheme {
        Greeting("Android")
    }
}