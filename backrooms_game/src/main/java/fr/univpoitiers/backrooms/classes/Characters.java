package fr.univpoitiers.backrooms.classes;

/**
 * Represents a non-player character (NPC) or an enemy in the game.
 * Characters are entities that can be placed in locations and can interact
 * with the player (e.g., combat).
 */
public class Characters extends Entity {

    /**
     * Constructs a new Character (Enemy or NPC).
     *
     * @param PV          Initial Health Points.
     * @param name        The name of the character.
     * @param attack      The attack power (damage dealt to the player).
     * @param description A brief description of the character's appearance.
     */
    public Characters(int PV, String name, int attack, String description){
        super(PV, name, attack, description);
    }

}