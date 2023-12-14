package com.ferit.ferit.repository

import com.ferit.ferit.models.Ingredient
import com.ferit.ferit.models.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class RecipeRepository(
  private val database: FirebaseFirestore = Firebase.firestore
) {

  suspend fun getRecipes(): List<Recipe> {
    val data = database.collection("recipes").get().await()
    val recipes = parseDocumentFromDatabase(data.documents)
    return recipes
  }

  suspend fun getRecipeById(id: String): Recipe {
    val data = database.collection("recipes").get().await()
    return data.documents.find { it.id == id }?.let { document ->
      parseRecipe(document)
    } ?: Recipe()
  }

  private fun parseDocumentFromDatabase(documents: List<DocumentSnapshot>): List<Recipe> {
    val recipes = mutableListOf<Recipe>()
    documents.forEach { document ->
      val recipe = parseRecipe(document)
      recipes.add(recipe)
    }
    return recipes;
  }

  private fun parseRecipe(document: DocumentSnapshot): Recipe {
    var recipe = document.toObject(Recipe::class.java)
    if (recipe != null) {
      val id = document.id
      val ingredientsMap =
        document.get("ingredients") as? List<Map<String, String>>
      if (ingredientsMap != null) {
        val ingredients = getIngredientsFromDocument(ingredientsMap)
        recipe = recipe.copy(ingredients = ingredients)
      }
      recipe = recipe.copy(id = id)
      return recipe
    } else {
      return Recipe()
    }
  }

  private fun getIngredientsFromDocument(ingredientsList: List<Map<String, String>>): List<Ingredient> {
    val ingredients = mutableListOf<Ingredient>()
    ingredientsList.forEach { ingredientMap ->
      val ingredient = Ingredient(
        image = ingredientMap["image"] ?: "",
        title = ingredientMap["title"] ?: "",
        subtitle = ingredientMap["subtitle"] ?: "",
      )
      ingredients.add(ingredient)
    }
    return ingredients
  }
}
