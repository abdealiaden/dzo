package dunzo;

public class RecipeIngredient {
	private Ingredient ingredient;
	private long quantity;
	RecipeIngredient(Ingredient ingredient, long quantity) throws Exception{
		if(quantity<0) {
			throw new Exception("Quantity cant be negative");
		}
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
