package fr.univpoitiers.backrooms.classes;

public class Spells extends Items {

    public Spells(String name, int volume, String description) {
        super(name, volume, description);
    }

    @Override
    public String getName() {return super.getName();}

    @Override
    public int getVolume() {return super.getVolume();}

    @Override
    public String getDescription() {return super.getDescription();}

    public String cast(Hero player) {
        return "You cast " + this.getName() + ". " + this.getDescription();
    }

    @Override
    public String use(Hero player) {
        return "";
    }

    @Override
    public String use(Hero player, Entity target) {
        return this.cast(player) + " on " + target.getName();
    }


}