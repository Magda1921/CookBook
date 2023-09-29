package cookbook;

import cookbook.Models.Ingredient;
import cookbook.Models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeMapper {
        public static String changeRecipeToString(Recipe recipe) {

            String ingredientsString = "";
            for (Ingredient ingredient : recipe.getIngredients()) {
                String ingredientString = ingredient.getId() + "-" + ingredient.getName() + "-" + ingredient.getQuantity() + "-" + ingredient.getUnit();
                ingredientsString += ingredientString + ",";
            }
            ingredientsString = ingredientsString.substring(0, ingredientsString.length() - 1);

            return recipe.getId() + ";" + recipe.getName() + ";" + recipe.getDescription() + ";" + ingredientsString;
        }

        public static Recipe changeStringToRecipe (String recipeString) {

            String[] partsRecipe = recipeString.split(";");
            int recipeIdPosition = 0;
            int recipeNamePosition = 1;
            int recipeDescriptionPosition = 2;
            int recipeIngredientsPosition = 3;
            String idRecipe = partsRecipe[recipeIdPosition];
            int id = Integer.parseInt(idRecipe);

            String nameRecipe = partsRecipe[recipeNamePosition];

            String descriptionRecipe = partsRecipe[recipeDescriptionPosition];

            String ingredientsRecipe = partsRecipe[recipeIngredientsPosition];
            String[] partsIngredients = ingredientsRecipe.split(",");

            int ingredientIdPosition = 0;
            int ingredientNamePosition = 1;
            int ingredientQuantityPosition = 2;
            int ingredientUnitPosition = 3;
            List<Ingredient> ingredients = new ArrayList<>();
            for (String ingredientString : partsIngredients) {
                String[] ingredientPart = ingredientString.split("-");
                Ingredient ingredient = new Ingredient();
                ingredient.setId(Integer.parseInt(ingredientPart[ingredientIdPosition]));
                ingredient.setName(ingredientPart[ingredientNamePosition]);
                ingredient.setQuantity(Double.parseDouble(ingredientPart[ingredientQuantityPosition]));
                ingredient.setUnit(ingredientPart[ingredientUnitPosition]);
                ingredients.add(ingredient);
            }

            Recipe recipe = new Recipe();
            recipe.setId(id);
            recipe.setName(nameRecipe);
            recipe.setDescription(descriptionRecipe);
            recipe.setIngredients(ingredients);

            return recipe;
        }
    }