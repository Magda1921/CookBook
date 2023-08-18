package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CookBook {

    private String fileName = "cookbook.txt";
    private int id;
    private RecipeMapper recipeMapper = new RecipeMapper();
    private FileOperations fileOperations = new FileOperations();

    public void start() throws IOException {

        id = getCurrentId(fileOperations.readRecipes(fileName));
        fileOperations.createFile();

        int number = -1;
        Scanner scanner = new Scanner(System.in);
        while (number != 0) {
            System.out.println("Please select one of option:");
            System.out.println("0 - exit program");
            System.out.println("1 - add recipe");
            System.out.println("2 - remove recipe");
            System.out.println("3 - show all");
            System.out.println("4 - find recipe by name");
            System.out.println("5 - update recipe by name");
            number = scanner.nextInt();
            if (number == 1) {
                System.out.println("User selected one");
                addRecipe();
            } else if (number == 2) {
                System.out.println("User selected two");
                removeRecipe();
            } else if (number == 3) {
                System.out.println("User selected three");
                showRecipes();
            } else if (number == 4) {
                System.out.println("Write the name of the recipe that you want to find");
                findRecipeByName();
            } else if (number == 5) {
                System.out.println("Write the id number of the recipe that you want to update");
                editByName();
            }
        }
    }

    public int getCurrentId(List <Recipe> recipes) {

        Recipe recipe = new Recipe();
        if (recipes == null || recipes.isEmpty()) {
            id = 0;
        } else {
                recipe = recipes.get(recipes.size()-1);
                id = recipe.getId();}
        return id;
    }

    private void showRecipes() {

        List<Recipe> recipes = fileOperations.readRecipes(fileName);

        System.out.println(recipes);
    }

    private void removeRecipe() throws IOException {
        List<Recipe> recipes = fileOperations.readRecipes(fileName);
        int removedRecipe = 0;
        System.out.println("Enter the name of the recipe that you want to remove");
        Scanner scanner = new Scanner(System.in);
        String wantedRecipeName;
        wantedRecipeName = scanner.nextLine();
        String nameofRecipe = "";
        for (int i = 0; i < recipes.size(); i++) {
            nameofRecipe = recipes.get(i).getName();
            if (nameofRecipe.equals(wantedRecipeName)) {
                removedRecipe = i;
                recipes.remove(removedRecipe);
                break;
            }
            fileOperations.removeFile();
            for (Recipe recipe : recipes) {
                saveRecipeStringToFile(recipeMapper.changeRecipeToString(recipe));
            }

        }
        System.out.println(recipes);
    }

    public List<Ingredient> addNewIngredient() {

        Scanner scanner = new Scanner(System.in);
        List<Ingredient> ingredients = new ArrayList<>();
        int ingredientId = 0;
        while (true) {

            String ingredientName = null;
            double ingredientQuantity = 0;
            String ingredientUnit = null;

            System.out.println("Enter next ingredients name or click x for exit");
            ingredientName = scanner.nextLine();
            if (ingredientName.equals("x")) {
                break;
            } else {

                System.out.println("Enter ingredients quantity");
                ingredientQuantity = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter ingredients unit");
                ingredientUnit = scanner.nextLine();

                ingredientId++;
                Ingredient ingredient = new Ingredient();
                ingredient.setId(ingredientId);
                ingredient.setName(ingredientName);
                ingredient.setQuantity(ingredientQuantity);
                ingredient.setUnit(ingredientUnit);
                ingredients.add(ingredient);

            }
        }
        return ingredients;
    }


    public void saveRecipeStringToFile(String recipeString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("cookbook.txt", true));
        writer.append(recipeString);
        writer.newLine();

        writer.close();
    }

    public void addRecipe() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter recipe's name: ");
        String name = scanner.nextLine();

        System.out.println("Enter recipe's description: ");
        String description = scanner.nextLine();


        List<Ingredient> ingredients = addNewIngredient();
        id++;

        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setIngredients(ingredients);

        saveRecipeStringToFile(recipeMapper.changeRecipeToString(recipe));

    }

    public void findRecipeByName() {
        Scanner scanner = new Scanner(System.in);
        String wantedRecipeName;
        wantedRecipeName = scanner.nextLine();
        String nameofRecipe = "";
        List<Recipe> recipes = fileOperations.readRecipes(fileName);
        for (int i = 0; i < recipes.size(); i++) {
            nameofRecipe = recipes.get(i).getName();
            if (nameofRecipe.equals(wantedRecipeName)) {
                System.out.println(recipes.get(i));
            }

        }

    }

    public void editByName() throws IOException {
        List<Recipe> recipes = fileOperations.readRecipes(fileName);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the recipe you want to update: ");
        String recipeName = scanner.nextLine();
        String nameofRecipe = "";
        int changedRecipe = 0;
        for (int i = 0; i < recipes.size(); i++) {
            nameofRecipe = recipes.get(i).getName();
            if (nameofRecipe.equals(recipeName)) {
                changedRecipe = i;
                break;
            }
        }

        System.out.println("Enter a new recipe's name: ");
        String newName = scanner.nextLine();

        System.out.println("Enter recipe's description: ");
        String description = scanner.nextLine();

        recipes.get(changedRecipe).setDescription(description);
        recipes.get(changedRecipe).setName(newName);


        List<Ingredient> ingredients = addNewIngredient();
        recipes.get(changedRecipe).setIngredients(ingredients);
        fileOperations.removeFile();

        for (Recipe recipe : recipes) {
            saveRecipeStringToFile(recipeMapper.changeRecipeToString(recipe));
        }
    }
}

