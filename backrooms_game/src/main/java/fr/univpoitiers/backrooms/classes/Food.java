package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.interfaces.Items;

public class Food implements Items {
    private String name;
    private int volume;
    private String description;
    private int healthPoints;

    public Food(String name, int volume, String description, int healthPoints) {
        if (description == null || name == null || volume < 0 || healthPoints < 0) {
            throw new UnsupportedOperationException("Food(4) error : Parameters cannot be null or negative");
        }
        this.name = name;
        this.volume = volume;
        this.description = description;
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return this.name;
    }

    public int getVolume() {
        return this.volume;
    }

    public String getDescription() {
        return this.description;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }
}
