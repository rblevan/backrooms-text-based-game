package fr.univpoitiers.backrooms.classes;

import java.util.ArrayList;
import java.util.List;

public class Backpack extends  Items {
    private static final int DEFAULT_CAPACITY = 100;
    private static final int BACKPACK_PHYSICAL_VOLUME = 10;

    private int capacity;
    private List<Items> items;
    private int usedVolume;
    private final int itemVolume;

    public Backpack(String name, String description, int capacity) {
        super(name, BACKPACK_PHYSICAL_VOLUME, description);
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.usedVolume = 0;
        this.itemVolume = BACKPACK_PHYSICAL_VOLUME;
    }


    public int getVolume() {
        return this.itemVolume; // Retourne sa taille, pas sa capacité
    }


    public int getUsedVolume() {
        return this.usedVolume;
    }

    public int getRemainingCapacity() {
        return this.capacity - this.usedVolume;
    }

    public int getCapacityMax() {
        return this.capacity;
    }

    public boolean addItem(Items item) {
        int itemVolume = item.getVolume();

        if (this.usedVolume + itemVolume <= this.capacity) {
            this.items.add(item);
            this.usedVolume += itemVolume; // CORRECTION : Mise à jour du volume utilisé
            return true;
        }
        return false;
    }

    public void removeItem(Items item) {
        if (this.items.remove(item)) {
            this.usedVolume -= item.getVolume(); // CORRECTION : Mise à jour du volume utilisé
        }
    }

    public List<Items> getItems() {
        return this.items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
        this.updateUsedVolume(); // Nouvelle méthode pour calculer le volume total
    }

    // Méthode utilitaire pour recalculer le volume utilisé
    private void updateUsedVolume() {
        this.usedVolume = 0;
        for (Items item : this.items) {
            this.usedVolume += item.getVolume();
        }
    }

    public void clear() {
        this.usedVolume = 0;
        this.items = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public Items getItemByName(String name) {
        for (Items item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}