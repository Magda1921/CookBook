package cookbook;

import cookbook.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileOperations {

    CookBook cookBook = new CookBook();
    public void crateFile() throws IOException {

        Path pathCookbook = Paths.get("cookbook.txt");
        if (Files.exists(pathCookbook)) {
            List<Recipe> recipes = cookBook.readRecipes();
            cookBook.id = recipes.get(recipes.size() - 1).getId() + 1;
        } else {
            File newFile = new File("cookbook.txt");
            boolean success = newFile.createNewFile();
            cookBook.id = 0;
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
}
