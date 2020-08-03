package coffeeMachine;

import java.util.HashMap;
import java.util.Map;

/***
 * POJO Object for given JSON.
 ***/
public class MachinePojo {
  Long outlets;
  TotalItemsQuantity totalItemsQuantity;
  BeveragesAndRequirements beverages;

  public Long getOutlets() {
    return outlets;
  }

  public void setOutlets(Long outlets) {
    this.outlets = outlets;
  }

  public TotalItemsQuantity getTotalItemsQuantity() {
    return totalItemsQuantity;
  }

  public void setTotalItemsQuantity(TotalItemsQuantity totalItemsQuantity) {
    this.totalItemsQuantity = totalItemsQuantity;
  }

  public BeveragesAndRequirements getBeverages() {
    return beverages;
  }

  public void setBeverages(BeveragesAndRequirements beverages) {
    this.beverages = beverages;
  }

  public class TotalItemsQuantity {
    Map<String, Long> items = new HashMap<>();

    public Map<String, Long> getItems() {
      return items;
    }

    public void setItems(Map<String, Long> items) {
      this.items = items;
    }
  }
  public class BeveragesAndRequirements {

    public Map<String, Map<String, Long>> getBeveragesAndRequirements() {
      return beveragesAndRequirements;
    }

    public void setBeveragesAndRequirements(
        Map<String, Map<String, Long>> beveragesAndRequirements) {
      this.beveragesAndRequirements = beveragesAndRequirements;
    }

    Map<String,Map<String,Long>> beveragesAndRequirements = new HashMap<>();
  }
}
