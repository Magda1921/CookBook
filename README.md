# Cookbook - recipe storage application
Simple recipe storage application created in order to learn programming.
Application has been created in Java 17.
The project includes junit tests to ensure its functionality. 
Application allows users to save recipes in a text file and modify it. 
User interface is in the console.

## Features
- Create and storage recipes such as name, description and ingredients.
- Save recipes to file.
- Remove recipe by name.
- Search for recipes by name.
- Edit recipe.

## Prerequisites 
Before you begin, ensure you have met the following requirements:
- Java 17 or higher installed on your system.
- Maven build tool to compile and run the application.

## To run this project 
Install Java 17 and run in console:
1. javac -d out CookBook.java Main.java FileOperations.java RecipeMapper.java model/Ingredient.java model/Recipe.java Properties.java
1. java -cp out cookbook.Main
