package fr.univpoitiers.backrooms.classes;

public class Exits {

    private final Locations destination;
    private final String exitDescription;
    private boolean isLocked;

    public Exits(Locations destination, String exitDescription, boolean isLocked) {
        this.destination = destination;
        this.exitDescription = exitDescription;
        this.isLocked = isLocked;
    }

    public Locations getDestination() {
        return destination;
    }

    public String getExitDescription() {
        return exitDescription;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {
        this.isLocked = false;
    }
}