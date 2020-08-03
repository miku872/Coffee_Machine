package coffeeMachine;

import coffeeMachine.MachinePojo.BeveragesAndRequirements;
import coffeeMachine.MachinePojo.TotalItemsQuantity;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/***
 * Dao implementation class which converts JSONObject to MachinePojo
 ***/

public class DaoImpl {
  public static final String machine = "machine";
  final static String total_items_quantity = "total_items_quantity";
  final static String beverages = "beverages";
  final static String outlets = "outlets";
  final static String count = "count_n";

  public MachinePojo getMachinePojo(String jsonPath) throws IOException, ParseException {
    JSONObject inputObject = (JSONObject) DaoHelper.getMachineObject(jsonPath);
    MachinePojo machinePojo = new MachinePojo();
    machinePojo.setOutlets((long)0);
    Long n = getOutlets(inputObject);
    machinePojo.setOutlets(n);
    TotalItemsQuantity totalItemsQuantity = getTotalTimesQuanity(inputObject,machinePojo);
    machinePojo.setTotalItemsQuantity(totalItemsQuantity);
    BeveragesAndRequirements beveragesAndRequirements = getBeveragesAndRequirements(inputObject,machinePojo);
    machinePojo.setBeverages(beveragesAndRequirements);
    return machinePojo;
  }

  private BeveragesAndRequirements getBeveragesAndRequirements(JSONObject inputObject, MachinePojo machinePojo) {
    BeveragesAndRequirements beveragesAndRequirements = machinePojo.new BeveragesAndRequirements();
    Map<String, Map<String, Long>> beveragesAndRequirementsMap = new HashMap<>();
    if(inputObject.containsKey(machine)) {
      JSONObject machineObject = (JSONObject) inputObject.get(machine);
      if (machineObject.containsKey(beverages)) {
        JSONObject beveragesObject = (JSONObject) machineObject.get(beverages);
        for (Object key : beveragesObject.keySet()) {
          Map<String, Long> beverageRequirementsMap = new HashMap<>();
          String item = (String) key;
          JSONObject beverageObject = (JSONObject) beveragesObject.get(item);
          for (Object beverageItem : beverageObject.keySet()) {
            String ingredient = (String) beverageItem;
            Long quantity = (Long) beverageObject.get(beverageItem);
            beverageRequirementsMap.put(ingredient, quantity);
          }
          beveragesAndRequirementsMap.put(item, beverageRequirementsMap);
        }
      }
    }
    beveragesAndRequirements.setBeveragesAndRequirements(beveragesAndRequirementsMap);
    return beveragesAndRequirements;
  }

  private TotalItemsQuantity getTotalTimesQuanity(JSONObject inputObject, MachinePojo machinePojo) {
    TotalItemsQuantity totalItemsQuantity = machinePojo.new TotalItemsQuantity();
    Map<String, Long> items = new HashMap<>();
    if(inputObject.containsKey(machine)) {
      JSONObject machineObject = (JSONObject) inputObject.get(machine);
      if(machineObject.containsKey(total_items_quantity)){
        JSONObject totalItemsQuantityObject = (JSONObject) machineObject.get(total_items_quantity);
        for(Object key : totalItemsQuantityObject.keySet()){
          String item = (String) key;
          Long quantity = (Long) totalItemsQuantityObject.get(item);
          items.put(item,quantity);
        }
      }
      totalItemsQuantity.setItems(items);
    }
    return totalItemsQuantity;
  }


  private Long getOutlets(JSONObject inputObject) {
    Long n = new Long(0);
    if(inputObject.containsKey(machine)) {
      JSONObject machineObject = (JSONObject) inputObject.get(machine);
      if (machineObject.containsKey(outlets)) {
        JSONObject outletObject = (JSONObject) machineObject.get(outlets);
        if (outletObject.containsKey(count)) {
          n = (long) outletObject.get(count);
        }
      }
    }
    return n;
  }
}
