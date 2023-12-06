package com.ferit.ferit.models

import com.ferit.ferit.R

val recipes = listOf(
  Recipe(
    id = 0,
    image = R.drawable.strawberry_pie_1,
    title = "Strawberry Pie",
    category = "Dessert",
    cookingTime = "50 min",
    energy = "620 kcal",
    rating = "4,9",
    description = "This dessert is very tasty and not difficult to prepare. Alsi you can replace strawberries with any other berry you like.",
    reviews = "reviews 0",
    ingredients = listOf(
      Ingredient(
        image = R.drawable.eggs,
        title = "Eggs",
        subtitle = "4"
      ),
      Ingredient(
        image = R.drawable.flour,
        title = "Flour",
        subtitle = "450g"
      ),
      Ingredient(
        image = R.drawable.juice,
        title = "Lemon juice",
        subtitle = "150 g"
      ),
      Ingredient(
        image = R.drawable.mint,
        title = "Mint",
        subtitle = "2 pcs"
      )
    )
  ),
  Recipe(
    id = 1,
    image = R.drawable.apple_pie,
    title = "Title 1",
    category = "Category 1",
    cookingTime = "Cooking Time 1",
    energy = "Energy 1",
    rating = "Rating 1",
    description = "Description 1",
    reviews = "reviews 1",
    ingredients = listOf(
      Ingredient(
        image = R.drawable.eggs,
        title = "Title 1",
        subtitle = "Subtitle 1"
      )
    )
  ),
  Recipe(
    id = 2,
    image = R.drawable.baklava,
    title = "Title 2",
    category = "Category 2",
    cookingTime = "Cooking Time 2",
    energy = "Energy 2",
    rating = "Rating 2",
    description = "Description 2",
    reviews = "reviews 2",
    ingredients = listOf(
      Ingredient(
        image = R.drawable.eggs,
        title = "Title 2",
        subtitle = "Subtitle 2"
      )
    )
  ),
)