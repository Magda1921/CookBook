package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cookbook.CookBook.fileName;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FileOperationsTest {

        String filenameTest = "cookbooktest.txt";
        FileOperations fileOperations = new FileOperations();

        @Test
        void shouldCreateFile() throws IOException {
            fileOperations.createFile();
            File file = new File(fileName);
            assertTrue(file.exists());
        }

        @Test
        void shouldRemoveFila() {
            fileOperations.removeFile();
            File file = new File(fileName);
            assertFalse(file.exists());
        }

        @Test
        void shouldReadRecipes() {
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
            List<Recipe> expected = List.of(recipe);

            List<Recipe> mapped = fileOperations.readRecipes(filenameTest);

            Assertions.assertEquals(expected, mapped);
        }
    }
