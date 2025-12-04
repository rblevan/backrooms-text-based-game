package fr.univpoitiers.backrooms.classes;

public class Hero extends Entity {

    private Backpack backpack;
    private final String username;
    private Locations location;

    public Backpack getBackpack() {
        return this.backpack;
    }

    public Hero(String username, int PV, String name, int attack, String description, Backpack backpack, Locations location){
        super(PV,name,attack,description);
        this.backpack = backpack;
        this.username = username;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }


    public Locations getLocation() {
        return location;
    }
}