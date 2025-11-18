package fr.univpoitiers.backrooms;

public class Hero extends Entity {
    public Hero(String description, String name) {
        super(description, name);
    }

    public Hero(int PV, String name, int attack, String description){
        super(PV, name, attack, description);
    }
}