import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Recipe> recipes = new ArrayList<>();
        int number = -1;
        Scanner scanner = new Scanner(System.in);
        while (number != 0) {
            System.out.println("Please select one of option:");
            System.out.println("0 - exit program");
            System.out.println("1 - add recipe");
            System.out.println("2 - remove recipe");
            System.out.println("3 - show all");
            number = scanner.nextInt();
            if (number == 1) {
                System.out.println("User selected one");
                addRecipe(recipes);
            }
            else if (number == 2) {
                System.out.println("User selected two");
            }
            else if (number == 3) {
                System.out.println("User selected three");
                showRecipes(recipes);
            }
        }
    }
    private static void showRecipes(List<Recipe> recipes) {
        System.out.println(recipes);
    }
    public static void addRecipe(List<Recipe> recipes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter recipe's name: ");
        String name = scanner.nextLine();

        System.out.println("Enter recipe's description: ");
        String description = scanner.nextLine();

        String ingredientName = null;
        List<String> ingredients = new ArrayList<>();

        while (true) {
            System.out.println("Enter next ingredients or click x for exit");
            ingredientName = scanner.nextLine();
            if (ingredientName.equals("x"))
                break;
        }
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setIngredients(ingredients);

        recipes.add(recipe);
    }
}