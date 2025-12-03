package fr.univpoitiers.backrooms.classes;


public class Weapon extends Items {
    private int damage;

    public Weapon(String name, int volume, String description, int damage) {
        super(name, volume, description);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public void Attack (Entity entity){
        entity.setPV(entity.getPV() - this.damage);
    }
}
