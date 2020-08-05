package dunzo;

import java.util.ArrayList;
import java.util.HashMap;
public class Outlet {
	private boolean isFree;
	Outlet(){
		this.isFree = true;
	}
	public synchronized void brew(ArrayList<Slot> slots, Recipe recipe, Object thisReference) throws IngredientNotAvailableException,IngredientNotSufficientException {
			this.isFree = false;
			System.out.println(recipe.getName()+" started brewing");
			//Getting hashmap of available ingredients to slot to easily access them when we match them with recipe ingredients
			synchronized(slots) { //This block is synchronized because if any we are checking availibility of slots and dispensing material through that, we dont want any race conditions here.
				HashMap<String, Slot> ingredientsAvailable = new HashMap<String, Slot>();
				slots.forEach(slot->{
					ingredientsAvailable.put(slot.getIngredient().getName(), slot);
				});
				for(RecipeIngredient recipeIngredient:recipe.getRecipeIngredients()) {
					if(!ingredientsAvailable.containsKey(recipeIngredient.getIngredient().getName())) { //Checking if recipe ingredient is available in the slot or not
						//As this slot can't be further utilized, we will be freeing the slot
						this.isFree = true;
						throw new IngredientNotAvailableException(recipeIngredient.getIngredient());
					}
					if(ingredientsAvailable.get(recipeIngredient.getIngredient().getName()).getQuantity()<recipeIngredient.getQuantity()) { //Checking if ingredient available is of sufficient quantity
						this.isFree=true;
						throw new IngredientNotSufficientException(recipeIngredient.getIngredient());
					}
				};
				//At this point everything seems sufficient, we can prepare our drink... Cheers
				for(RecipeIngredient recipeIngredient:recipe.getRecipeIngredients()) {
					ingredientsAvailable.get(recipeIngredient.getIngredient().getName()).dispenseIngredient(recipeIngredient.getQuantity());
				}
			}
			try {
				Thread.sleep(1000); //Coffee preparation takes some time to process
			} catch(InterruptedException e) {
				//Refill recipe ingredient here
				System.out.println("Coffee cannot be processed because the process has been interrupted");
			}
			
			System.out.println(recipe.getName()+" brewed");
			this.isFree = true;
			synchronized(thisReference) {
				thisReference.notify(); //Notify if anyone waiting for availibility of outlets
			}
	}
	public boolean isAvailable() {
		return isFree;
	}
	public void setBusy() {
		this.isFree = false;
	}
}
