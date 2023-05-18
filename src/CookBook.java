import java.util.List;

public class CookBook {

    private List<Recipe> recipes;

    public CookBook() {
    }

    public CookBook(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "CookBook{" +
                "recipes=" + recipes +
                '}';
    }
}
