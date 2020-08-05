package dunzo;

public class RecipeIngredient {
	private Ingredient ingredient;
	private int quantity;
	RecipeIngredient(Ingredient ingredient, int quantity){
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	public int getQuantity() {
		return this.quantity;
	}
}
