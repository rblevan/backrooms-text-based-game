package fr.univpoitiers.backrooms.classes;

public class Hero extends Entity {
    public Hero(String name, Locations location) {
        super(name, location);
    }

    public Hero(int PV, String name, int attack, String description, Locations location){
        super(PV, name, attack, description, location);
    }


}