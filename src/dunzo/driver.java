package dunzo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class driver {
	public static void main(String args[]) {
		Machine machine = new Machine(null, null, 3);
		for(int i=0;i<10;i++) {
			try {
				machine.brew(null);
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
