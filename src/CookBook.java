import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CookBook {

    static int id;


    public void start() throws IOException {

        Path pathCookbook = Paths.get("cookbook.txt");
        if (Files.exists(pathCookbook)) {
            System.out.println("file exists");
            List<Recipe> recipes = readRecipes();
            id = recipes.size();
        } else {
            File newFile = new File("cookbook.txt");
            boolean success = newFile.createNewFile();
            id = 0;
        }


        int number = -1;
        Scanner scanner = new Scanner(System.in);
        while (number != 0) {
            System.out.println("Please select one of option:");
            System.out.println("0 - exit program");
            System.out.println("1 - add recipe");
            System.out.println("2 - remove recipe");
            System.out.println("3 - show all");
            System.out.println("4 - find recipe by name");
            System.out.println("5 - update recipe by id");
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

    private void showRecipes() {

        List<Recipe> recipes = readRecipes();

        System.out.println(recipes);
    }

    private void removeRecipe() {
        File file = new File("cookbook.txt");
        boolean fileDeleted = file.delete();
        if (fileDeleted) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("File not deleted successfully");
        }
    }

    private List<Recipe> readRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String file = "cookbook.txt";
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] partsRecipe = currentLine.split(";");

                String idRecipe = partsRecipe[0];
                int id = Integer.parseInt(idRecipe);

                String nameRecipe = partsRecipe[1];

                String descriptionRecipe = partsRecipe[2];

                String ingredientsRecipe = partsRecipe[3];
                String[] partsIngredients = ingredientsRecipe.split(",");

                List<Ingredient> ingredients = new ArrayList<>();
                for (String ingredientString : partsIngredients) {
                    String[] ingredientPart = ingredientString.split("-");
                    Ingredient ingredient = new Ingredient();
                    ingredient.setId(Integer.parseInt(ingredientPart[0]));
                    ingredient.setName(ingredientPart[1]);
                    ingredient.setQuantity(Double.parseDouble(ingredientPart[2]));
                    ingredient.setUnit(ingredientPart[3]);
                    ingredients.add(ingredient);
                }


                Recipe recipe = new Recipe();
                recipe.setId(id);
                recipe.setName(nameRecipe);
                recipe.setDescription(descriptionRecipe);
                recipe.setIngredients(ingredients);
                currentLine = reader.readLine();
                recipes.add(recipe);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("There is problem with file read. Contact IT");
            return null;
        }
        return recipes;
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

    public void changeIngredientToString(List<Ingredient> ingredients, Recipe recipe) throws IOException {


        String ingredientsString = "";
        for (Ingredient ingredient : ingredients) {
            String ingredientString = ingredient.getId() + "-" + ingredient.getName() + "-" + ingredient.getQuantity() + "-" + ingredient.getUnit();
            ingredientsString += ingredientString + ",";
        }

        ingredientsString = ingredientsString.substring(0, ingredientsString.length() - 1);

        String recipeString = recipe.getId() + ";" + recipe.getName() + ";" + recipe.getDescription() + ";" + ingredientsString;


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


        changeIngredientToString(ingredients, recipe);


    }


    public void findRecipeByName() {
        Scanner scanner = new Scanner(System.in);
        String wantedRecipeName;
        wantedRecipeName = scanner.nextLine();
        String nameofRecipe = "";
        List<Recipe> recipes = readRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            nameofRecipe = recipes.get(i).getName();
            if (nameofRecipe.equals(wantedRecipeName)) {
                System.out.println(recipes.get(i));
            }

        }

    }

    public void editByName() throws IOException {
        List<Recipe> recipes = readRecipes();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the recipe you want to update: ");
        String recipeName = scanner.nextLine();
        String nameofRecipe = "";
        int chngedRecipe = 0;
        for (int i = 0; i < recipes.size(); i++) {
            nameofRecipe = recipes.get(i).getName();
            if (nameofRecipe.equals(recipeName)) {
                chngedRecipe = i;
                break;
            }
        }

        System.out.println("Enter a new recipe's name: ");
        String newName = scanner.nextLine();

        System.out.println("Enter recipe's description: ");
        String description = scanner.nextLine();

        recipes.get(chngedRecipe).setDescription(description);
        recipes.get(chngedRecipe).setName(newName);


        List<Ingredient> ingredients = addNewIngredient();
        recipes.get(chngedRecipe).setIngredients(ingredients);
        removeRecipe();

        for (Recipe recipe : recipes) {
            changeIngredientToString(ingredients, recipe);

        }
    }
}

