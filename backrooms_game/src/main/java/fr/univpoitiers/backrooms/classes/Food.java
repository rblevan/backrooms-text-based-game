package fr.univpoitiers.backrooms.classes;

/**
 * Represents a consumable food item.
 * Food can be used to restore the player's Health Points (PV).
 */
public class Food extends Items {

    private int healPoints;

    /**
     * Constructs a new Food item.
     *
     * @param name        The name of the food.
     * @param volume      The volume.
     * @param description The description.
     * @param healPoints  The amount of health this food restores when consumed.
     */
    public Food(String name, int volume, String description, int healPoints) {
        super(name, volume, description);
        this.healPoints = healPoints;
    }

    /**
     * Retrieves the healing power of the food.
     *
     * @return The number of PV restored.
     */
    public int getHealPoints() {
        return this.healPoints;
    }

    @Override
    public String use(Hero player) {
        player.setPV(player.getPV() + this.getHealPoints());
        player.getBackpack().removeItem(this);
        return player.getName() + " has been healed by " + this.getHealPoints() + " points.";
    }

    @Override
    public String use(Hero player, Entity target) {
        return "";
    }
}
