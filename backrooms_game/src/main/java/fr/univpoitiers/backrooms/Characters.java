package fr.univpoitiers.backrooms;

public class Characters extends Entity {
    public Characters(String description, String name) {
        super(description, name);
    }

    public Characters(int PV, String name, int attack, String description){
        super(PV, name, attack, description);
    }

}