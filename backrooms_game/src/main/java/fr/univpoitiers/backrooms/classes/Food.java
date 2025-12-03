package fr.univpoitiers.backrooms.classes;

public class Food extends Items {
    private int healPoints;

    public Food(String name, int volume, String description, int healPoints) {
        super(name, volume, description);
        this.healPoints = healPoints;
    }



    public int getHealPoints() {
        return this.healPoints;
    }
}
