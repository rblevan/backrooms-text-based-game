package fr.univpoitiers.backrooms;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements Item {
    private static final int DEFAULT_CAPACITY = 100;

    private String name = "Backpack";
    private int capacity;
    private List<Item> items = new ArrayList<>();
    private int usedVolume = 0;

    public Backpack() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public String getName() {
        return this.name;
    }

    public int getVolume() {
        return this.capacity;
    }

    public int getUsedVolume() {
        return this.usedVolume;
    }

    public boolean addItem(Item item) {
        if (getUsedVolume() + Item.getVolume <= this.capacity) {
            this.items.add(item);
            return true;
        }
        return false;
    }

    public List<Item> getItems() {
        return this.items;
    }
}