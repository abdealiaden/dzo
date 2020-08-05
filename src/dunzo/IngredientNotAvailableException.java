package dunzo;

public class IngredientNotAvailableException extends Exception{
	private Ingredient ingredient;
	IngredientNotAvailableException(Ingredient ingredient){
		this.ingredient = ingredient;
	}
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	public String toString() {
		return "Coffee cannot be prepared because "+this.ingredient.getName()+" is not available";
	}
}
