package dunzo;

import java.util.ArrayList;

public class Recipe {
	private String name;
	private ArrayList<RecipeIngredient> recipeIngredients;
	Recipe(String name, ArrayList<RecipeIngredient> recipeIngredients){
		this.name = name;
		this.recipeIngredients = recipeIngredients;
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<RecipeIngredient> getRecipeIngredients(){
		return this.recipeIngredients;
	}
}
