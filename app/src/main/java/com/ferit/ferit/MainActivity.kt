package com.ferit.ferit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ferit.ferit.details.RecipeDetailsScreen
import com.ferit.ferit.home.RecipesScreen
import com.ferit.ferit.models.recipes
import com.ferit.ferit.ui.theme.FeritTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FeritTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          /*RecipesScreen()*/
          RecipeDetailsScreen(recipe = recipes[0])
        }
      }
    }
  }
}

@Composable
fun Greeting() {
  Text(text = "Hello world!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  Text(text = "Hello world!", color = Color.Green)
}


