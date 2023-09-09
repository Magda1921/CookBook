# Cookbook - recipe storage application
Simple recipe storage application created in order to learn programming.
Application has been created in Java 17.
Application allows users to save recipes in a text file and modify it. 
User interface is in the console.

## Prerequisites 
Before you begin, ensure you have met the following requirements:
- Java 17 or higher installed on your system.
- Maven build tool to compile and run the application.

## To run this project 
Install Java 17 and run in console:
1. javac -d out CookBook.java Main.java FileOperations.java RecipeMapper.java model/Ingredient.java model/Recipe.java Properties.java
1. java -cp out cookbook.Main
