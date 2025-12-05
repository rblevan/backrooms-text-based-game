package fr.univpoitiers.backrooms.classes;

/**
 * Represents a magical spell item.
 * Spells trigger special effects or descriptions when cast on a target.
 */
public class Spells extends Items {

    /**
     * Constructs a new Spell.
     *
     * @param name        The name of the spell.
     * @param volume      The volume (usually small for spells/scrolls).
     * @param description The description of the spell's effect.
     */
    public Spells(String name, int volume, String description) {
        super(name, volume, description);
    }

    @Override
    public String getName() {return super.getName();}

    @Override
    public int getVolume() {return super.getVolume();}

    @Override
    public String getDescription() {return super.getDescription();}

    /**
     * Generates the string description of the spell being cast.
     * Helper method used by the use() methods.
     *
     * @param player The hero casting the spell.
     * @return A string describing the casting action.
     */
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