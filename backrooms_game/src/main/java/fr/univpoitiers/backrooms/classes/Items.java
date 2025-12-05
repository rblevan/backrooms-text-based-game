package fr.univpoitiers.backrooms.classes;

public abstract class Items {

    private String Name;
    private int Volume;
    private String Description;

    /**
     * Constructs a new Item.
     * Validates that parameters are valid (not null and volume not negative).
     *
     * @param name        The name of the item (used for commands like TAKE or LOOK).
     * @param volume      The space the item occupies in the backpack.
     * @param description A brief description of the item.
     * @throws UnsupportedOperationException If parameters are null or volume is negative.
     */
    public Items(String name, int volume, String description) {
        if (name == null || volume < 0 || description == null) {
            throw new UnsupportedOperationException("Items(3) error : Parameters cannot be null or negative");
        }
        this.Name = name;
        this.Volume = volume;
        this.Description = description;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The item's name.
     */
    public String getName() {
        return this.Name;
    }

    /**
     * Retrieves the volume (size/weight) of the item.
     * Used to calculate backpack capacity.
     *
     * @return The volume value.
     */
    public int getVolume() {
        return this.Volume;
    }

    /**
     * Retrieves the description of the item.
     * Displayed when the player LOOKs at the item.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.Description;
    }

    /**
     * Abstract method defining how an item is used on the player themselves.
     * Typically implemented by consumable items like Food.
     *
     * @param player The hero using the item.
     * @return A string describing the effect of the usage.
     */
    public abstract String use(Hero player);

    /**
     * Abstract method defining how an item is used on a specific target.
     * Typically implemented by interactive items like Weapons or offensive Spells.
     *
     * @param player The hero using the item.
     * @param target The entity being targeted (e.g., an enemy).
     * @return A string describing the result of the interaction.
     */
    public abstract String use(Hero player, Entity target);


}
