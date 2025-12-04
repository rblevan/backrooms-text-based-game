package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {

    private final String title;
	private final String description;
    private final Map<Direction,Exits> exits;
    private Map<String,Items> items;
    private final Map<String,Characters> characters;

    public Locations(String title, String description) {
        this.title = title;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
        this.characters = new HashMap<>();
    }

    public Map<String, Characters> getCharacters() {
        return characters;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<Direction,Exits> getExits(){
        return this.exits;
    }

    public String getTitle() {
        return this.title;
    }

    public Map<String,Items> getItems() {
        return this.items;
    }

    public void addExit(Direction direction , Exits exit){
        this.exits.put(direction,exit);
    }

    public Items getItemByName(String name) {
        for (String key : items.keySet()) {
            if (key.equalsIgnoreCase(name)) {
                return items.get(key);
            }
        }
        return null;
    }

    public void addItem(Items item) {
        this.items.put(item.getName(),item);
    }

    public void removeItem(Items item) {
        this.items.remove(item.getName());
    }

    public void addCharacter(Characters character){
        this.characters.put(character.getName(),character);
    }

    public Characters getCharacterByName(String name){
        return this.characters.get(name);
    }

    public void removeCharacter(Characters character){
        this.characters.remove(character.getName());
    }
}