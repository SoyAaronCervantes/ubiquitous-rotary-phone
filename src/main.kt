const val MAINMENU = """
  :: Bienvenido a Recipe Maker ::
    
  Selecciona la opción deseada
    1. Hacer una receta
    2. Ver mis recetas
    3. Salir
""";

const val ADDINGREDIENT = """
  Quieres agregar otro ingrediente?
  1. Si
  2. No
"""

const val SPACELINES = "*************************************************\n";

fun main() {
  var endProgram = false
  val ingredientsList = listOf("Agua", "Leche", "Carne", "Verduras", "Frutas", "Cereal", "Huevos", "Aceite")
  var recipeList: List< List<String> > = listOf()

  do when ( showMainMenu() ) {
    1 -> {
      val recipe = createRecipe( ingredientsList )
      recipeList = recipeList.plusElement( recipe )
    }
    2 -> {
      recipeList(recipeList)
    }
    3 -> {
      println("Vuelve pronto!");
      endProgram = true;
    }
    else -> {
      println("Lo lamento, escoge un número entre el 1 y 3");
      println(SPACELINES);
      showMainMenu()
    }
  }
  while (!endProgram)
}

fun createRecipe(ingredientsList: List<String>): List<String> {
  var recipe = listOf<String>();
  var recipeWithCurrentIngredients = ingredientsList;
  var canShow = true

  while ( canShow ) {

    println("Estos son los ingredientes:");

    if ( recipeWithCurrentIngredients.isNotEmpty() ) {

      showIngredients( recipeWithCurrentIngredients );

      println("Selecciona un ingrediente, y lo agregamos a tu receta:");

      val index = readLine()?.toInt()?.dec()

      if ( index !== null ) {

        val ingredient = addIngredientToRecipe( index, recipeWithCurrentIngredients );
        recipe = recipe.plus( ingredient );
        recipeWithCurrentIngredients = removeIngredient( index, recipeWithCurrentIngredients );

      }

      println(ADDINGREDIENT)
      canShow = readLine()?.toInt() == 1

    } else {

      canShow = false;

    }

  }

  return recipe;
}

fun recipeList( recipesList: List< List<String> > ) {
  val stayInRecipe = true

  while ( stayInRecipe ) {
    showRecipes( recipesList );
    println("\n$SPACELINES")
    println("Si quieres volver presiona 0")
    var index = readLine()?.toInt() ?: 0

    if ( index == 0 ) break

    index = index.dec()

    if ( recipesList[ index ].isNullOrEmpty() ) {
      println( "Debes seleccionar un numero entre el 1 y ${ recipesList.size }" )
    } else {
      showIngredients( recipesList[ index ] )
    }

    println(SPACELINES)

  }
}

fun showRecipes( recipesList: List< List<String> > ) {
  for ( ( i, recipe ) in recipesList.withIndex() ) {
    println("Receta ${ i.inc() }, Ingredientes: ${ recipe.size }")
    println("Si quieres ver sus ingredientes presiona: ${i.inc()}")
  }
}

fun showIngredients(ingredientsList: List<String>) {
  for ( ( index, ingredient ) in ingredientsList.withIndex() ) {
    println("${ index.inc() } - $ingredient")
  }

}

fun showMainMenu(): Int {
  println(MAINMENU);
  return readLine()?.toInt() ?: 4;
}

fun addIngredientToRecipe( index: Int, ingredientsList: List<String> ): String {
  val ingredient = ingredientsList[ index ]
  println("Ingrediente agregado!")
  println(SPACELINES);
  return ingredient
}

fun removeIngredient(index: Int, ingredientsList: List<String> ): List<String> {
  val list = ingredientsList.map { it };
  return list.filter { ingredientsList[index] !== it }
}