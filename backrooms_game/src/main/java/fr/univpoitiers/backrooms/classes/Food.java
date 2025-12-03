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

    @Override
    public String use(Hero player) {
        player.setPV(player.getPV() + this.getHealPoints());
        return player.getName() + "has been healed by " + this.getHealPoints() + " points.";
    }

    @Override
    public String useWith(Items target, Hero player) {
        return "Nothing interesting happens when you use " + this.getName() + " with " + target.getName() + ".";
    }
}
