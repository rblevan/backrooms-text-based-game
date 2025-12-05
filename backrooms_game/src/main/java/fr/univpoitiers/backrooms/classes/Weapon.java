package fr.univpoitiers.backrooms.classes;

/**
 * Represents a weapon item.
 * Weapons have a specific damage value and are used to attack entities
 * via the 'ATTACK' command.
 */
public class Weapon extends Items {
    private int damage;

    /**
     * Constructs a new Weapon.
     *
     * @param name        The name of the weapon.
     * @param volume      The space it occupies in the backpack.
     * @param description A description of the weapon.
     * @param damage      The amount of damage this weapon inflicts.
     */
    public Weapon(String name, int volume, String description, int damage) {
        super(name, volume, description);
        this.damage = damage;
    }

    /**
     * Retrieves the damage value of the weapon.
     *
     * @return The damage points.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Performs the attack logic on a target entity.
     * Reduces the target's PV (Health Points) by the weapon's damage value.
     *
     * @param entity The target entity being attacked.
     */
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
