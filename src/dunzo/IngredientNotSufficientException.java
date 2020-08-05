package dunzo;

public class IngredientNotSufficientException extends Exception{
	private Ingredient ingredient;
	IngredientNotSufficientException(Ingredient ingredient){
		this.ingredient = ingredient;
	}
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	public String toString() {
		return "Coffee cannot be prepared because "+this.ingredient.getName()+" is not suffiecient";
	}
}
