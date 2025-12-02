package fr.univpoitiers.backrooms.classes;

public class Hero extends Entity {
    public Hero(String name) {
        super(name);
    }

    public Hero(int PV, String name, int attack, String description){
        super(PV, name, attack, description);
    }
}