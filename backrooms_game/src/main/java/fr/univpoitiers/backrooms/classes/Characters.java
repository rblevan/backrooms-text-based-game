package fr.univpoitiers.backrooms.classes;

public class Characters extends Entity {
    public Characters(String description, String name, Locations location) {
        super(description, name, location);
    }

    public Characters(int PV, String name, int attack, String description, Locations location){
        super(PV, name, attack, description, location);
    }

}