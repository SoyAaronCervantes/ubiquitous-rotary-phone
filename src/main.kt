const val MAINMENU = """
  :: Bienvenido a Recipe Maker ::
    
  Selecciona la opción deseada
    1. Hacer una receta
    2. Ver mis recetas
    3. Salir
""";

const val ADDINGREDIENT = """
  Quieres agregar otra categoría?
  1. Si
  2. No
"""

const val SPACELINES = "*************************************************\n";

fun main() {
  var endProgram = false
  val ingredients = listOf("Agua", "Leche", "Carne", "Verduras", "Frutas", "Cereal", "Huevos", "Aceite")
  var recipes: List< List<String> > = listOf()

  do when ( showMainMenu() ) {
    1 -> {
      val recipe = createRecipe( ingredients )
      recipes = recipes.plusElement( recipe )
    }
    2 -> {
      recipes(recipes)
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

fun createRecipe(ingredients: List<String>): List<String> {
  var recipe = listOf<String>();
  var recipeWithCurrentIngredients = ingredients;
  var canShow = true

  while ( canShow ) {

    println("Selecciona por categoría el ingrediente que buscas:");

    if ( recipeWithCurrentIngredients.isNotEmpty() ) {

      showIngredients( recipeWithCurrentIngredients );

      println("Selecciona una categoría, y lo agregamos a tu receta:");

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

fun recipes( recipes: List< List<String> > ) {
  val stayInRecipe = true

  while ( stayInRecipe ) {
    showRecipes( recipes );
    println("\n$SPACELINES")
    println("Si quieres volver presiona 0")
    var index = readLine()?.toInt() ?: 0

    if ( index == 0 ) break

    index = index.dec()

    if ( recipes[ index ].isNullOrEmpty() ) {
      println( "Debes seleccionar un numero entre el 1 y ${ recipes.size }" )
    } else {
      showIngredients( recipes[ index ] )
    }

    println(SPACELINES)

  }
}

fun showRecipes( recipes: List< List<String> > ) {
  for ( ( i, recipe ) in recipes.withIndex() ) {
    println("Receta ${ i.inc() }, Categorías: ${ recipe.size }")
    println("Si quieres ver las categorías presiona: ${i.inc()}")
  }
}

fun showIngredients(ingredients: List<String>) {
  for ( ( index, ingredient ) in ingredients.withIndex() ) {
    println("${ index.inc() } - $ingredient")
  }

}

fun showMainMenu(): Int {
  println(MAINMENU);
  return readLine()?.toInt() ?: 4;
}

fun addIngredientToRecipe( index: Int, ingredients: List<String> ): String {
  val ingredient = ingredients[ index ]
  println("Categoría agregado!")
  println(SPACELINES);
  return ingredient
}

fun removeIngredient( index: Int, ingredients: List<String> ): List<String> {
  val list = ingredients.map { it };
  return list.filter { ingredients[index] !== it }
}