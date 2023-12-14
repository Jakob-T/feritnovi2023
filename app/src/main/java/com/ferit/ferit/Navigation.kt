package com.ferit.ferit

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferit.ferit.details.DetailsPage
import com.ferit.ferit.home.HomeScreen

object Routes {

  const val SCREEN_ALL_RECIPES = "recipeList"
  const val SCREEN_RECIPE_DETAILS = "recipeDetails/{recipeId}"
  fun getRecipeDetailsPath(recipeId: String): String {
    return "recipeDetails/$recipeId"
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
      HomeScreen(navController)
    }
    composable(
      Routes.SCREEN_RECIPE_DETAILS,
      arguments = listOf(
        navArgument("recipeId") {
          type = NavType.StringType
        }
      )
    ) { backStackEntry ->
      backStackEntry.arguments?.getString("recipeId")?.let { idFromArguments ->
        DetailsPage(
          navController = navController,
          recipeId = idFromArguments
        )
      }
    }
  }
}