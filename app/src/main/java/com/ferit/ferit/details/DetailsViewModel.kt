package com.ferit.ferit.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferit.ferit.models.Recipe
import com.ferit.ferit.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class DetailsState {
  object Loading : DetailsState()
  data class Success(val state: Recipe) : DetailsState()
}
class DetailsViewModel(
  private val repository: RecipeRepository = RecipeRepository()
) : ViewModel() {

  val state = MutableStateFlow<DetailsState>(DetailsState.Loading)

  fun getRecipe(id: String) {
    viewModelScope.launch {
      val result = repository.getRecipeById(id)
      state.value = DetailsState.Success(result)
    }
  }
}