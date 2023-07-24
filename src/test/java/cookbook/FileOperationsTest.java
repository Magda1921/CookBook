package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class FileOperationsTest {
    FileOperations fileOperations = new FileOperations();
    @Test
    void shouldCreateFile () throws IOException {
        fileOperations.createFile();
        File file = new File("cookbook.txt");
        assertTrue(file.exists());
    }

    @Test
    void shouldRemoveFila () {
        fileOperations.removeFile();
        File file = new File("cookbook.txt");
        Assertions.assertFalse(file.exists());
    }

    @Test
   void shouldReadRecipes () {
       List <Recipe> mapped = fileOperations.readRecipes("cookbooktest.txt");

        Recipe recipe = new Recipe();
        List <Recipe> expected = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        List < Ingredient> ingredients = new ArrayList<>();
        recipe.setId(1);
        recipe.setName("pesto");
        recipe.setDescription("dodaj bazylię, zblenduj");

        ingredient1.setId(1);
        ingredient1.setName("bazylia");
        ingredient1.setQuantity(1);
        ingredient1.setUnit("szt");

        ingredient2.setId(2);
        ingredient2.setName("czosnek");
        ingredient2.setQuantity(1);
        ingredient2.setUnit("główka");

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        recipe.setIngredients(ingredients);
        expected.add(recipe);

        Assertions.assertEquals(mapped, expected);

    }
}
