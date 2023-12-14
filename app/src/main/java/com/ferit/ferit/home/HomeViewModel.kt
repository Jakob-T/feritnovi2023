package com.ferit.ferit.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferit.ferit.models.Recipe
import com.ferit.ferit.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class HomeState {
  object Loading : HomeState()
  data class Success(val state: List<Recipe>) : HomeState()
}

class HomeViewModel(
  private val repository: RecipeRepository = RecipeRepository()
) : ViewModel() {
  init {
    viewModelScope.launch {
      getRecipes()
    }
  }

  val state = MutableStateFlow<HomeState>(HomeState.Loading)

  private suspend fun getRecipes() {
    val result = repository.getRecipes()
    state.value = HomeState.Success(result)
  }
}