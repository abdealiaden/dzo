package dunzo;

public class RecipeIngredient {
	private Ingredient ingredient;
	private long quantity;
	RecipeIngredient(Ingredient ingredient, long quantity){
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	public long getQuantity() {
		return this.quantity;
	}
}
