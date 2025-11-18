package fr.univpoitiers.backrooms.classes;

public class Weapon {
    private String name;
    private int volume;
    private String description;
    private int damage;

    public Weapon(String name, int volume, int damage) {
        if (name == null || volume < 0 || damage < 0) {
            throw new UnsupportedOperationException("Weapon(4) error : Parameters cannot be null or negative");
        }
        this.name = name;
        this.volume = volume;
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
