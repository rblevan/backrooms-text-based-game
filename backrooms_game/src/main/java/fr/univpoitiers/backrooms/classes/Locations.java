package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {

    private final String title;
	private final String description;
    private final Map<Direction, Locations> exits;
    private Direction nextLocation;
    private List<String> items;

    public Locations(String title, String Description){
        this.title = title;
        this.description = Description;
        this.exits = new HashMap<>();
        this.nextLocation = null;
        this.items = new ArrayList<>();
    }


	public String getDescription() {
        return this.description;
	}

    public Map<Direction,Locations> getExits(){
        return this.exits;
    }

    public String getTitle() {
        return this.title;
    }

    public Direction getNextLocation() {
        return this.nextLocation;
    }

    public List<String> getItems() {
        return this.items;
    }


}