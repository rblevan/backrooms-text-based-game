package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a specific location (room, area, or level) in the game world.
 * It holds descriptions, a list of exits to other locations,
 * and collections of items and characters present in the area.
 */
public class Locations {

    private final String title;
	private final String description;
    private final Map<Direction,Exits> exits;
    private Map<String,Items> items;
    private final Map<String,Characters> characters;

    /**
     * Constructs a new Location with a title and a detailed description.
     * Initializes empty maps for exits, items, and characters.
     *
     * @param title       The short name or title of the location.
     * @param description The detailed narrative description displayed to the player.
     */
    public Locations(String title, String description) {
        this.title = title;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
        this.characters = new HashMap<>();
    }

    /**
     * Retrieves the map of characters currently present in this location.
     *
     * @return A Map linking names to Character objects.
     */
    public Map<String, Characters> getCharacters() {
        return characters;
    }

    /**
     * Returns the narrative description of the location.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the available exits from this location.
     *
     * @return A Map linking Directions (NORTH, SOUTH...) to Exit objects.
     */
    public Map<Direction,Exits> getExits(){
        return this.exits;
    }

    /**
     * Returns the title of the location.
     *
     * @return The title string.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Retrieves the map of items currently on the floor in this location.
     *
     * @return A Map linking names to Item objects.
     */
    public Map<String,Items> getItems() {
        return this.items;
    }

    /**
     * Adds an exit to this location, connecting it to another one.
     *
     * @param direction The direction of the exit (e.g., NORTH, SOUTH).
     * @param exit      The Exit object containing the destination and properties.
     */
    public void addExit(Direction direction , Exits exit){
        this.exits.put(direction,exit);
    }

    /**
     * Searches for an item in the room by its name.
     * The search is case-insensitive.
     *
     * @param name The name of the item to find.
     * @return The Item object if found, or null otherwise.
     */
    public Items getItemByName(String name) {
        for (String key : items.keySet()) {
            if (key.equalsIgnoreCase(name)) {
                return items.get(key);
            }
        }
        return null;
    }

    /**
     * Places an item in the room.
     * Used during initialization or when the player drops an item.
     *
     * @param item The item to add to the room.
     */
    public void addItem(Items item) {
        this.items.put(item.getName(),item);
    }

    /**
     * Removes an item from the room.
     * Usually called when the player picks up an item (TAKE command).
     *
     * @param item The item to remove.
     */
    public void removeItem(Items item) {
        this.items.remove(item.getName());
    }

    /**
     * Adds a character (NPC or Enemy) to the location.
     *
     * @param character The character entity to add.
     */
    public void addCharacter(Characters character){
        this.characters.put(character.getName(),character);
    }

    /**
     * Searches for a character in the room by name.
     * Case-insensitive search.
     *
     * @param name The name of the character.
     * @return The Character object if found, or null.
     */
    public Characters getCharacterByName(String name){
        for (Characters character : this.characters.values()) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }

    /**
     * Removes a character from the location.
     * Usually called when a character is defeated or moves.
     *
     * @param character The character to remove.
     */
    public void removeCharacter(Characters character){
        this.characters.remove(character.getName());
    }
}