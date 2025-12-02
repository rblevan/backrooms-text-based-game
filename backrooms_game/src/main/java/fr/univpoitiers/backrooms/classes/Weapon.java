package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.interfaces.Items;

public class Weapon implements Items {
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void Attack (Entity entity){
        entity.setPV(entity.getPV() - this.damage);
    }
}
