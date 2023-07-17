package cookbook.model;

import cookbook.model.Ingredient;

import java.util.List;

public class Recipe {


        private int id;
        private String name;
        private String description;
        List<Ingredient> ingredients;

        public Recipe() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        @Override
        public String toString() {
            return "Recipe{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", ingredients=" + ingredients +
                    '}';
        }
    }

