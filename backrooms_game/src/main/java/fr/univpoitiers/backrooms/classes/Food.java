package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.interfaces.Items;

public class Food implements Items {
    private String name;
    private int volume;
    private String description;
    private int healPoints;

    public Food(String name, int volume, String description, int healPoints) {
        if (description == null || name == null || volume < 0 || healPoints < 0) {
            throw new UnsupportedOperationException("Food(4) error : Parameters cannot be null or negative");
        }
        this.name = name;
        this.volume = volume;
        this.description = description;
        this.healPoints = healPoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public int getHealPoints() {
        return this.healPoints;
    }
}
