package fr.univpoitiers.backrooms.classes;

public class Hero extends Entity {

    private Backpack backpack;

    public Backpack getBackpack() {
        return this.backpack;
    }


    public Hero(String name, Locations location) {
        super(name, location);
        this.backpack = new Backpack("Backpack", "A backpack for carrying items.", 10);
    }

    public Hero(int PV, String name, int attack, String description, Locations location, Backpack backpack){
        super(PV, name, attack, description, location);
        this.backpack = backpack;
    }



}