package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;
import fr.univpoitiers.backrooms.interfaces.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {

    private final String title;
    private final String description;
    private final Map<Direction, Locations> exits;
    private final List<Items> items;

    public Locations(String title, String description) {
        this.title = title;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public String getDescription() {
        return this.description;
    }

    public Map<Direction, Locations> getExits() {
        return this.exits;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Items> getItems() {
        return this.items;
    }

    public void addExit(Direction direction, Locations location) {
        this.exits.put(direction, location);
    }

    public void addItem(Items item) {
        this.items.add(item);
    }

    public Items findItemByName(String name) {
        for (Items item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(Items item) {
        this.items.remove(item);
    }
}
