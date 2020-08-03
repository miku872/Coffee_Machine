package coffeeMachine;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/***
 * Helper class for parsing given JSON.
 ***/
public class DaoHelper {
  public static Object getMachineObject(String jsonPath) throws IOException, ParseException {
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonPath));
    return jsonObject;
  }
}
