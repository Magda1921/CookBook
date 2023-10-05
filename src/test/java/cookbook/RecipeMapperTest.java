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
        List<Ingredient> ingredients = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("name");
        recipe.setDescription("description");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1);
        ingredient1.setName("ingredient");
        ingredient1.setQuantity(1.0);
        ingredient1.setUnit("unit");

        ingredients.add(ingredient1);

        recipe.setIngredients(ingredients);

        String mapped = RecipeMapper.changeRecipeToString(recipe);
        String expected = "1;name;description;1-ingredient-1.0-unit";
        Assertions.assertEquals(expected, mapped);
    }
    @Test
    void shouldChangeStringToRecipe() {
        String recipeString = "1;name;description;1-ingredient-1.0-unit";

        Recipe mapped = RecipeMapper.changeStringToRecipe(recipeString);
        List<Ingredient> ingredients = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("name");
        recipe.setDescription("description");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1);
        ingredient1.setName("ingredient");
        ingredient1.setQuantity(1.0);
        ingredient1.setUnit("unit");

        ingredients.add(ingredient1);

        recipe.setIngredients(ingredients);


        Assertions.assertEquals(recipe, mapped);
    }
}