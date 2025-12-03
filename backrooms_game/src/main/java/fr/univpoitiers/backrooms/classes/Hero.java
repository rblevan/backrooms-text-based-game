package fr.univpoitiers.backrooms.classes;

public class Hero extends Entity {

    private Backpack backpack;

    public Backpack getBackpack() {
        return this.backpack;
    }

    public Hero(int PV, String name, int attack, String description, Locations location, Backpack backpack){
        super(PV, name, attack, description, location);
        this.backpack = backpack;
    }



}