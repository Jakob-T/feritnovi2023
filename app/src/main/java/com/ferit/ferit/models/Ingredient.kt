package com.ferit.ferit.models

import androidx.annotation.DrawableRes

data class Ingredient(
  @DrawableRes val image: Int,
  val title: String,
  val subtitle: String
)