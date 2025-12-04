package fr.univpoitiers.backrooms.classes;

public class Hero extends Entity {

    private Backpack backpack;
    private final String username;

    public Backpack getBackpack() {
        return this.backpack;
    }

    public Hero(String username, int PV, String name, int attack, String description, Locations location, Backpack backpack){
        super(PV, name, attack, description);
        this.backpack = backpack;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }




}