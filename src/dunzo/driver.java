package dunzo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class driver {
	public static void main(String args[]) {
		Ingredient milk = new Ingredient("milk");
		Ingredient water = new Ingredient("water");
		Ingredient tea = new Ingredient("tea");
		Ingredient sugar = new Ingredient("sugar");
		Ingredient coffee = new Ingredient("coffee");
	    Slot slot1 = new Slot(milk, 1000,1000);
	    Slot slot2 = new Slot(water, 1000,1000);
	    Slot slot3 = new Slot(tea, 100,100);
	    Slot slot4 = new Slot(sugar, 500,500);
	    Slot slot5 = new Slot(coffee, 300,300);
	    ArrayList<Slot> slots = new ArrayList<Slot>();
	    slots.add(slot1);
	    slots.add(slot2);
	    slots.add(slot3);
	    slots.add(slot4);
	    slots.add(slot5);
	    RecipeIngredient teaIngredient1 = new RecipeIngredient(milk, 100);
	    RecipeIngredient teaIngredient2 = new RecipeIngredient(water, 100);
	    RecipeIngredient teaIngredient3 = new RecipeIngredient(tea, 5);
	    RecipeIngredient teaIngredient4 = new RecipeIngredient(sugar, 40);
	    ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>();
	    recipeIngredients.add(teaIngredient1);
	    recipeIngredients.add(teaIngredient2);
	    recipeIngredients.add(teaIngredient3);
	    recipeIngredients.add(teaIngredient4);
	    Recipe milktea = new Recipe("tea", recipeIngredients);
//	    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
//	    recipes.add(milktea);
		Machine machine = new Machine(slots, 100);
		for(int i=0;i<100;i++) {
			try {
				machine.brew(milktea);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			
		}
//		Ingredient milk = Ingredient.MILK;
//		JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader("employees.json"))
//        {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//            JSONObject employeeList = (JSONObject) obj;
//            System.out.println(employeeList.get("Ali"));
//            System.out.println(employeeList);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
	}
	
}
