package com.ferit.ferit

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferit.ferit.details.RecipeDetailsScreen
import com.ferit.ferit.home.RecipesScreen
import com.ferit.ferit.models.recipes

object Routes {

  const val SCREEN_ALL_RECIPES = "recipeList"
  const val SCREEN_RECIPE_DETAILS = "recipeDetails/{recipeId}"
  fun getRecipeDetailsPath(recipeId: Int?): String {
    if (recipeId != null && recipeId != -1) {
      return "recipeDetails/$recipeId"
    }
    return "recipeDetails/0"
  }
}

@Composable
fun NavigationController() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Routes.SCREEN_ALL_RECIPES
  ) {
    composable(Routes.SCREEN_ALL_RECIPES) {
      RecipesScreen(/*navigation = navController*/)
    }
    composable(
      Routes.SCREEN_RECIPE_DETAILS,
      arguments = listOf(
        navArgument("recipeId") {
          type = NavType.IntType
        }
      )
    ) { backStackEntry ->
      backStackEntry.arguments?.getInt("recipeId")?.let {
        RecipeDetailsScreen(
          /*navigation = navController,*/
          recipe = recipes[it]
        )
      }
    }
  }
}