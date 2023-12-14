package com.ferit.ferit.models

import androidx.annotation.DrawableRes

data class Recipe(
  val id: String = "",
  val image: String = "",
  val title: String = "",
  val category: String = "",
  val cookingTime: String = "",
  val energy: String = "",
  val rating: Int = 0,
  val description: String = "",
  val reviews: String = "",
  val ingredients: List<Ingredient> = emptyList()
)