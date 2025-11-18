package fr.univpoitiers.backrooms;

public class Hero extends Entity {

    public Hero(String description, String name){
        super(description, name);
    }
    public Hero(int PV, String description, int attack, String name){
        super(PV, description, attack, name);
    }
}