package com.ferit.ferit.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ferit.ferit.R
import com.ferit.ferit.home.IconButton
import com.ferit.ferit.models.Recipe
import com.ferit.ferit.ui.theme.DarkGray
import com.ferit.ferit.ui.theme.Gray
import com.ferit.ferit.ui.theme.Pink
import com.ferit.ferit.ui.theme.Purple500
import com.ferit.ferit.ui.theme.Transparent
import com.ferit.ferit.ui.theme.White

@Composable
fun RecipeDetailsScreen(
  /*navController: NavController,*/
  recipe: Recipe
) {
  val scrollState = rememberLazyListState()
  LazyColumn(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start,
    state = scrollState,
    modifier = Modifier
      .fillMaxSize()
  ) {
    item {
      TopImageAndBar(recipe.image)
      ScreenInfo (recipe.title, recipe.category)
      BasicInfo(recipe)
      Description(recipe)
      /*Servings()
      IngredientsHeader()*/
      IngredientsList(recipe)
      ShoppingListButton()
      Reviews(recipe)
      OtherRecipes()
    }
  }
}

@Composable
fun IngredientCard(
  @DrawableRes iconResource: Int,
  title: String,
  subtitle: String
) {
  Column(
  ) {
    Spacer(modifier = Modifier.height(8.dp))
    Image(
      painter = painterResource(id = iconResource),
      contentDescription = title,
      modifier = Modifier
        .align(alignment = Alignment.CenterHorizontally)
        .width(70.dp)
        .height(70.dp),
      contentScale = ContentScale.Fit

    )
    Text(
      text = title,
      style = MaterialTheme.typography.bodySmall.copy(
        fontWeight = FontWeight.Medium
      )
    )
    Text(
      text = subtitle,
      style = MaterialTheme.typography.bodySmall.copy(
        color = DarkGray
      )
    )
  }
}

@Composable
fun CircularButton(
  @DrawableRes iconResource: Int, color: Color = Gray,
  elevation: ButtonElevation? = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
  onClick: () -> Unit = {}
) {
  Button(
    contentPadding = PaddingValues(),
    elevation = elevation,
    onClick = { onClick() },
    colors = ButtonDefaults.buttonColors(
      containerColor = White,
      contentColor = color
    ),
    shape = RoundedCornerShape(5.dp), modifier = Modifier
      .width(38.dp)
      .height(38.dp)
  ) {
    Icon(
      painter = painterResource(id = iconResource),
      contentDescription = null,
    )
  }
}

@Composable
fun TopImageAndBar(
  @DrawableRes coverImage: Int
) {
  Box(
  ) {
    Image(
      painter = painterResource(id = coverImage), contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxSize()
    )
    Column(
      modifier = Modifier.fillMaxHeight()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .height(56.dp)
          .padding(horizontal = 16.dp)
      ) {
        CircularButton(R.drawable.ic_arrow_back)
        CircularButton(R.drawable.ic_favorite)
      }
    }
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(
          brush = Brush.verticalGradient(
            colors = listOf(
              Color.Transparent,
              Color.White
            ),
            startY = 100f
          )
        )
        .align(alignment = Alignment.BottomCenter)
    )
  }
}

@Composable
fun ScreenInfo(
  title: String,
  category: String
) {
  Column {
    Text(
      text = category,
      style = TextStyle(
        color = Purple500,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light
      ),
      modifier = Modifier.padding(horizontal = 16.dp)
    )
    Text(
      text = title,
      style = TextStyle(
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier.padding(horizontal = 16.dp)
    )
  }
}

@Composable
fun InfoColumn(
  @DrawableRes iconResource: Int,
  text: String
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(
      painter = painterResource(id = iconResource), contentDescription = null,
      tint = Pink,
      modifier = Modifier.height(24.dp)
    )
    Text(text = text, fontWeight = FontWeight.Bold)
  }
}

@Composable
fun BasicInfo(recipe: Recipe) {
  Row(
    horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
      .fillMaxWidth()
      .padding(top = 16.dp)
  ) {
    InfoColumn(R.drawable.ic_clock, recipe.cookingTime)
    InfoColumn(R.drawable.ic_flame, recipe.energy)
    InfoColumn(R.drawable.ic_star, recipe.rating)
  }
}

@Composable
fun Description(
  recipe: Recipe
) {
  Text(
    text = recipe.description,
    fontWeight = FontWeight.Medium,
    modifier = Modifier
      .padding(horizontal = 16.dp, vertical = 20.dp)
  )
}

@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
  Column(Modifier.padding(16.dp)) {
    for (i in items.indices step nColumns) {
      Row {
        for (j in 0 until nColumns) {
          if (i + j < items.size) {
            Box(
              contentAlignment = Alignment.TopCenter,
              modifier = Modifier.weight(1f)
            ) {
              content(items[i + j])
            }
          } else {
            Spacer(Modifier.weight(1f, fill = true))
          }
        }
      }
    }
  }
}

@Composable
fun IngredientsList(
  recipe: Recipe
) {
  EasyGrid(nColumns = 3, items = recipe.ingredients) {
    IngredientCard(it.image, it.title, it.subtitle)
  }
}

@Composable
fun ShoppingListButton() {
  Button(
    onClick = { },
    elevation = null,
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.Transparent,
      contentColor = Color.Black
    ),
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    Text(
      text = "Add to shopping list", modifier =
      Modifier.padding(8.dp)
    )
  }
}

@Composable
fun Reviews(recipe: Recipe) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
      .fillMaxWidth()
      .padding(PaddingValues(start = 16.dp))
  ) {
    Column {
      Text(text = "Reviews", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
      Text(text = recipe.reviews, color = DarkGray)
    }
    IconButton(
      iconResource = R.drawable.ic_arrow_right,
      text = "See all",
      colors = ButtonDefaults.buttonColors(containerColor = Transparent, contentColor = Pink),
      /*side = 1*/
    )
  }
}

@Composable
fun OtherRecipes() {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.strawberry_pie_2),
      contentDescription = "Strawberry Pie",
      modifier = Modifier
        .weight(1f)
        .clip(RoundedCornerShape(12.dp))
    )
    Spacer(modifier = Modifier.width(16.dp))
    Image(
      painter = painterResource(id = R.drawable.strawberry_pie_3),
      contentDescription = "Strawberry Pie",
      modifier = Modifier
    )
  }
}
