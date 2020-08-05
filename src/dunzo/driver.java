package dunzo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class driver {
	public static void main(String args[]) {
//		Ingredient milk = new Ingredient("milk");
//		Ingredient water = new Ingredient("water");
//		Ingredient tea = new Ingredient("tea");
//		Ingredient sugar = new Ingredient("sugar");
//		Ingredient coffee = new Ingredient("coffee");
//	    Slot slot1 = new Slot(milk, 1000,1000);
//	    Slot slot2 = new Slot(water, 1000,1000);
//	    Slot slot3 = new Slot(tea, 100,100);
//	    Slot slot4 = new Slot(sugar, 500,500);
//	    Slot slot5 = new Slot(coffee, 300,300);
//	    ArrayList<Slot> slots = new ArrayList<Slot>();
//	    slots.add(slot1);
//	    slots.add(slot2);
//	    slots.add(slot3);
//	    slots.add(slot4);
//	    slots.add(slot5);
//	    RecipeIngredient teaIngredient1 = new RecipeIngredient(milk, 100);
//	    RecipeIngredient teaIngredient2 = new RecipeIngredient(water, 100);
//	    RecipeIngredient teaIngredient3 = new RecipeIngredient(tea, 5);
//	    RecipeIngredient teaIngredient4 = new RecipeIngredient(sugar, 40);
//	    ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>();
//	    recipeIngredients.add(teaIngredient1);
//	    recipeIngredients.add(teaIngredient2);
//	    recipeIngredients.add(teaIngredient3);
//	    recipeIngredients.add(teaIngredient4);
//	    Recipe milktea = new Recipe("tea", recipeIngredients);
////	    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
////	    recipes.add(milktea);
//		Machine machine = new Machine(slots, 100);
//		for(int i=0;i<100;i++) {
//			try {
//				machine.brew(milktea);
//			} catch(Exception e) {
//				System.out.println(e.toString());
//			}
//			
//		}
//		Ingredient milk = Ingredient.MILK;
		/*Reading the file from JSON 
		 * Input file name is input.json
		 */
		JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("input.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject coffeeMachine = (JSONObject) obj;
            JSONObject machine = (JSONObject)coffeeMachine.get("machine");
            int numOutlets = Integer.parseInt(((JSONObject)machine.get("outlets")).get("count_n").toString());
            JSONObject ingredientsWithQuantity = (JSONObject) machine.get("total_items_quantity");
            //Creating ingredient and slots out of it
            HashMap<String, Ingredient> ingredientsMap = new HashMap<String, Ingredient>();
            ArrayList<Slot> slots = new ArrayList<Slot>();
            for(Object ingredient: ingredientsWithQuantity.keySet()) {
            	Ingredient i = new Ingredient(ingredient.toString());
            	ingredientsMap.put(ingredient.toString(), i);
            	long quantity = ((long) ingredientsWithQuantity.get(ingredient));
            	//System.out.println(quantity);
            	Slot s = new Slot(i, quantity, 10000);
            	slots.add(s);
            }
            //Getting the machine ready
            Machine cofMachine = new Machine(slots, numOutlets);
            //Getting recipes and brewing it...
            JSONObject beverages = (JSONObject) machine.get("beverages");
            ArrayList<Recipe> recipes = new ArrayList<Recipe>();
            for(Object beverage: beverages.keySet()) {
            	//Get recipe 
            	JSONObject recipeIngredients = (JSONObject) beverages.get(beverage);
            	ArrayList<RecipeIngredient> recipeIngredientsList = new ArrayList<RecipeIngredient>();
            	for(Object recipeIngredient: recipeIngredients.keySet() ) {
            		//Check if ingredient available
            		RecipeIngredient ri;
            		long quantity = (long)recipeIngredients.get(recipeIngredient);
            		if(!ingredientsMap.containsKey(recipeIngredient.toString())) {
            			//First add ingredient
            			Ingredient i = new Ingredient(recipeIngredient.toString());
            			ingredientsMap.put(recipeIngredient.toString(), i);
            		}
            		ri = new RecipeIngredient(ingredientsMap.get(recipeIngredient.toString()),quantity);
            		recipeIngredientsList.add(ri);
            	}
            	Recipe recipe = new Recipe(beverage.toString(), recipeIngredientsList);
            	recipes.add(recipe);
            }
            //Now brew everything in the recipe
            
        	for(Recipe r: recipes) {
        		new Thread(new Runnable() {
        			public void run() {
        				cofMachine.brew(r);
        			}
        		}).start();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
}
