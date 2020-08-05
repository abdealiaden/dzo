package dunzo;
//Assumption :- only one slot available for each ingredient
public class Slot { //Slot in the machine represents a container where ingredient is filled
	private Ingredient ingredient;
	private int quantity; //Quantity in ml
	private int maxQuantity; //This represents maximum quantity of ingredient allowed per slot
	Slot(Ingredient ingredient, int quantity, int maxQuantity){
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.maxQuantity = maxQuantity;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	public void refillSlot() { // This function refills the slot
		this.quantity = this.maxQuantity;
		this.toggleBeepIfNecessary();
	}
	public void refillSlotBy(int quanity) { //This function refills the slot by certain amount not exceeding the maximum permissible limit (maxQuantity)
		this.quantity = Integer.max((this.quantity + quantity), maxQuantity);
		this.toggleBeepIfNecessary();
	}
	public void dispenseIngredient(int quantity) throws IngredientNotSufficientException { //This function takes out the ingredient from the slot to brew coffee. It returns true if ingredient available else it returns false
		if(quantity<=this.quantity) {
			this.quantity = this.quantity - quantity;
			this.toggleBeepIfNecessary();
		} else {
			throw new IngredientNotSufficientException(this.ingredient);
		}
	}
	private void toggleBeepIfNecessary() {
		if(this.quantity <= 0.25*this.maxQuantity) {
			//Beep alarm for low quantity
		} else {
			//Stop alarm for low quantity
		}
	}
}
