package fr.univpoitiers.backrooms.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Backpack, which is an Item that can hold other items.
 * It manages a list of items within a specific volumetric capacity.
 * Handles adding, removing, and searching for items, as well as tracking used volume.
 */
public class Backpack extends Items {
    private static final int DEFAULT_CAPACITY = 100;
    private static final int BACKPACK_PHYSICAL_VOLUME = 10;

    private int capacity;
    private List<Items> items;
    private int usedVolume;
    private final int itemVolume;

    /**
     * Constructs a new empty Backpack.
     *
     * @param name        The name of the backpack.
     * @param description A brief description of the backpack.
     * @param capacity    The maximum storage capacity of the backpack (in volume units).
     */
    public Backpack(String name, String description, int capacity) {
        super(name, BACKPACK_PHYSICAL_VOLUME, description);
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.usedVolume = 0;
        this.itemVolume = BACKPACK_PHYSICAL_VOLUME;
    }

    /**
     * Returns the physical volume of the backpack itself (not its content).
     *
     * @return The volume of the backpack object.
     */
    public int getVolume() {
        return this.itemVolume; 
    }

    /**
     * Returns the total volume currently occupied by items inside the backpack.
     *
     * @return The current used volume.
     */
    public int getUsedVolume() {
        return this.usedVolume;
    }


    /**
     * Calculates the remaining free space in the backpack.
     *
     * @return The remaining capacity (Max Capacity - Used Volume).
     */
    public int getRemainingCapacity() {
        return this.capacity - this.usedVolume;
    }

    /**
     * Returns the maximum capacity of the backpack.
     *
     * @return The maximum capacity.
     */
    public int getCapacityMax() {
        return this.capacity;
    }

    /**
     * Attempts to add an item to the backpack.
     * Checks if there is enough remaining capacity before adding.
     *
     * @param item The item to be added.
     * @return true if the item was successfully added; false if the backpack is full.
     */
    public boolean addItem(Items item) {
        int itemVolume = item.getVolume();

        if (this.usedVolume + itemVolume <= this.capacity) {
            this.items.add(item);
            this.usedVolume += itemVolume;
            return true;
        }
        return false;
    }

    /**
     * Removes a specific item from the backpack and updates the used volume.
     *
     * @param item The item to remove.
     */
    public void removeItem(Items item) {
        if (this.items.remove(item)) {
            this.usedVolume -= item.getVolume();
        }
    }

    /**
     * Returns the list of items in the backpack.
     *
     * @return The list of items.
     */
    public List<Items> getItems() {
        return this.items;
    }

    /**
     * Set the items in the backpack.
     *
     * @param items
     */
    public void setItems(List<Items> items) {
        this.items = items;
        this.updateUsedVolume();
    }

    /**
     * Updates the used volume based on the items in the backpack.
     */
    private void updateUsedVolume() {
        this.usedVolume = 0;
        for (Items item : this.items) {
            this.usedVolume += item.getVolume();
        }
    }

    /**
     * Clears the backpack, removing all items and resetting the used volume.
     */
    public void clear() {
        this.usedVolume = 0;
        this.items = new ArrayList<>();
    }

    /**
     * Test if the backpack is empty.
     *
     * @return true if the backpack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * Searches for an item in the backpack by its name.
     * The search is case-insensitive.
     *
     * @param name The name of the item to search for.
     * @return The Item object if found, or null if not found.
     */
    public Items getItemByName(String name) {
        for (Items item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Generic use method inherited from Items.
     * Currently, a backpack cannot be "used" directly on the player in this context.
     *
     * @param player The hero owning the backpack.
     * @return An empty string or error message.
     */
    @Override
    public String use(Hero player) {
        return "";
    }

    /**
     * Gereric use method inherited from Items.
     * Currently, a backpack cannot be "used" directky on the player in this context.
     *
     * @param player
     * @param target
     * @return An empty string or error message.
     */
    @Override
    public String use(Hero player, Entity target) {
        return "";
    }


}