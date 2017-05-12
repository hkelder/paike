package ee.kg.paike.saaremaapaike.model;

import java.util.List;

public class FoodPlace {
    public String name;
    public List<FoodPlaceMenuItem> menuItems;

    public FoodPlace() {}

    public FoodPlace(String name, List<FoodPlaceMenuItem> menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }
}
