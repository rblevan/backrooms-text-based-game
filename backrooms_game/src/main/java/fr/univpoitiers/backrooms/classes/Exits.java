package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

/**
 * Represents an exit or a passage leading from one location to another.
 * It stores the destination location, a description of the passage,
 * and its state (locked or unlocked).
 */
public class Exits {

    private final Locations destination;
    private final String exitDescription;
    private boolean isLocked;

    /**
     * Constructs a new Exit.
     *
     * @param destination     The Location this exit leads to.
     * @param exitDescription A brief description of the exit (e.g., "A heavy iron door").
     * @param isLocked        Initial state of the exit (true if locked, false if open).
     */
    public Exits(Locations destination, String exitDescription, boolean isLocked) {
        this.destination = destination;
        this.exitDescription = exitDescription;
        this.isLocked = isLocked;
    }

    /**
     * Retrieves the destination location of this exit.
     * Used by the game engine to move the player when they go through this exit.
     *
     * @return The destination Location object.
     */
    public Locations getDestination() {
        return destination;
    }

    /**
     * Retrieves the description of the exit.
     * Can be used to list available exits to the player.
     *
     * @return The description string.
     */
    public String getExitDescription() {
        return exitDescription;
    }

    /**
     * Checks if the exit is currently locked.
     * If true, the player cannot pass through without unlocking it first.
     *
     * @return true if the exit is locked, false otherwise.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Unlocks the exit, allowing players to pass through.
     * Typically called when a specific key or condition is met.
     */
    public void unlock() {
        this.isLocked = false;
    }
}