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

    public void attack (Entity entity){
        entity.setPV(entity.getPV() - this.damage);
    }

    @Override
    public String use(Hero player) {
        return "";
    }

    @Override
    public String use(Hero player, Entity target) {
        attack(target);
        return "You strike " + target.getName() + " with your " + this.getName() +
                ", dealing " + this.getDamage() + " damage!";
    }

}
