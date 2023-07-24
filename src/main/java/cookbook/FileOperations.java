package cookbook;

import cookbook.model.Recipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static cookbook.RecipeMapper.changeStringToRecipe;

public class FileOperations {
    public void createFile() throws IOException {

        Path pathCookbook = Paths.get("cookbook.txt");
        if (Files.exists(pathCookbook)) {
        } else {
            File newFile = new File("cookbook.txt");
            newFile.createNewFile();
        }
    }

    public void removeFile() {
        try {
            File file = new File("cookbook.txt");
            if (file.delete()) {
                System.out.println(file.getName() + "deleted");
            } else System.out.println("failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> readRecipes(String fileName) {
        List<Recipe> recipes = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                Recipe recipe = new Recipe();
                recipe = changeStringToRecipe(currentLine);
                currentLine = reader.readLine();
                recipes.add(recipe);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("There is a problem with file read. Contact IT");
            return null;
        }
        return recipes;
    }
}
