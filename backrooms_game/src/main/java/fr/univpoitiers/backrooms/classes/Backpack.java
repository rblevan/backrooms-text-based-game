package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.interfaces.Items;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements Items {
    private static final int DEFAULT_CAPACITY = 100;

    private String name = "Backpack";
    private int capacity;
    private List<Items> items = new ArrayList<>();
    private int usedVolume = 0;

    public Backpack() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getVolume() {
        return this.capacity;
    }

    @Override
    public String getDescription() {
        return "";
    }

    public int getUsedVolume() {
        return this.usedVolume;
    }

    public boolean addItem(Items item) {
        if (getUsedVolume() + item.getVolume() <= this.capacity) {
            this.items.add(item);
            return true;
        }
        return false;
    }

    public List<Items> getItems() {
        return this.items;
    }
}