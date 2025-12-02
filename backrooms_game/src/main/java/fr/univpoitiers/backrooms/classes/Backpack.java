package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.interfaces.Items;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements Items {
    private static final int DEFAULT_CAPACITY = 100;
    private static final int BACKPACK_PHYSICAL_VOLUME = 10;

    private final String name;
    private String description;
    private int capacity;
    private List<Items> items;
    private int usedVolume;
    private final int itemVolume;

    public Backpack(String description) {
        this.name = "Backpack";
        this.description = description;
        this.capacity = DEFAULT_CAPACITY;
        this.items = new ArrayList<>();
        this.usedVolume = 0;
        this.itemVolume = BACKPACK_PHYSICAL_VOLUME; // Initialisation
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getVolume() {
        return this.itemVolume; // 👈 Retourne sa taille, pas sa capacité
    }

    @Override
    public String getDescription() {
        // Retourne la description spécifique du sac, pas une chaîne vide
        return this.description;
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
            this.usedVolume += itemVolume; // 👈 CORRECTION : Mise à jour du volume utilisé
            return true;
        }
        return false;
    }

    public void removeItem(Items item) {
        if (this.items.remove(item)) {
            this.usedVolume -= item.getVolume(); // 👈 CORRECTION : Mise à jour du volume utilisé
        }
    }

    public List<Items> getItems() {
        return this.items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
        this.updateUsedVolume(); // 👈 Nouvelle méthode pour calculer le volume total
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
}