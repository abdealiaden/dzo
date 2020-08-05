package dunzo;
public class CannotBeBrewedException extends Exception{
	private Recipe recipe;
	CannotBeBrewedException(Recipe recipe){
		this.recipe = recipe;
	}
	public Recipe getRecipe() {
		return this.recipe;
	}
	public String toString() {
		return "Coffee cannot be prepared because "+this.recipe.getName()+" is not suffiecient";
	}
}

