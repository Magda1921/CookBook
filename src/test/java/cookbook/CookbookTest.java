package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CookbookTest {

    CookBook cookBook = new CookBook();
    @Test
    void shouldReturnCurrentId() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("pesto");
        recipe.setDescription("Place the basil leaves, garlic and olive oil into the bowl of a food processor and pulse several times.");
        List<Ingredient> ingredients = new ArrayList<>();
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

        List <Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        int mapped = cookBook.getCurrentId(recipes);
        int expected = 2;
    }
}
