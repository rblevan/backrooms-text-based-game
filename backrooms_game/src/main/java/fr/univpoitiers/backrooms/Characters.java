package fr.univpoitiers.backrooms;

public class Characters extends Entity {

    public Characters(int PV, String description, int attack, String name){
        super(PV, description, attack, name);
    }
    public Characters(String description, String name){
        super(description, name);
    }
}