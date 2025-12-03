package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;
import fr.univpoitiers.backrooms.interfaces.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {

    private final String title;
	private final String description;
    private final Map<Direction,Exits> exits;
    private List<Items> items;

    public Locations(String title, String Description){
        this.title = title;
        this.description = Description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
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

    public List<Items> getItems() {
        return this.items;
    }

    public void addExit(Direction direction , Exits exit){
        this.exits.put(direction,exit);
    }

}