package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RecipeMapperTest {
    @Test
    void shouldChangeRecipeToString() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("pesto");
        recipe.setDescription("Place the basil leaves, garlic and olive oil into the bowl of a food processor and pulse several times.");
        List <Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1);
        ingredient1.setName("basil");
        ingredient1.setQuantity(1);
        ingredient1.setUnit("piece");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2);
        ingredient2.setName("garlic");
        ingredient2.setQuantity(4);
        ingredient2.setUnit("cloves");
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        recipe.setIngredients(ingredients);

        String mapped = RecipeMapper.changeRecipeToString(recipe);
        String expected = "1;pesto;Place the basil leaves, garlic and olive oil into the bowl of a food processor and pulse several times.;1-basil-1.0-piece,2-garlic-4.0-cloves";
        Assertions.assertEquals(expected, mapped);
    }
    @Test
    void shouldChangeStringToRecipe() {
        String recipeString = "1;pesto;description;1-basil-1.0-piece,2-garlic-4.0-cloves";

        Recipe mapped = RecipeMapper.changeStringToRecipe(recipeString);
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("pesto");
        recipe.setDescription("description");
        List <Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1);
        ingredient1.setName("basil");
        ingredient1.setQuantity(1);
        ingredient1.setUnit("piece");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2);
        ingredient2.setName("garlic");
        ingredient2.setQuantity(4);
        ingredient2.setUnit("cloves");
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        recipe.setIngredients(ingredients);


        Assertions.assertEquals(recipe, mapped);
    }
}