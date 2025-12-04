package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Exits;
import fr.univpoitiers.backrooms.classes.Locations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExitsTest {

    private Locations destination;
    private Exits lockedExit;
    private Exits unlockedExit;

    @BeforeEach
    void setUp() {
        destination = new Locations("Next Room", "A mysterious room lies ahead.");
        lockedExit = new Exits(destination, "A large, locked oak door.", true);
        unlockedExit = new Exits(destination, "An open doorway.", false);
    }

    @Test
    void testConstructor() {
        assertEquals(destination, lockedExit.getDestination());
        assertEquals("A large, locked oak door.", lockedExit.getExitDescription());
        assertTrue(lockedExit.isLocked());

        assertEquals(destination, unlockedExit.getDestination());
        assertEquals("An open doorway.", unlockedExit.getExitDescription());
        assertFalse(unlockedExit.isLocked());
    }

    @Test
    void testGetDestination() {
        assertEquals(destination, lockedExit.getDestination());
    }

    @Test
    void testGetExitDescription() {
        assertEquals("A large, locked oak door.", lockedExit.getExitDescription());
    }

    @Test
    void testIsLocked() {
        assertTrue(lockedExit.isLocked());
        assertFalse(unlockedExit.isLocked());
    }

    @Test
    void testUnlock() {
        assertTrue(lockedExit.isLocked());
        lockedExit.unlock();
        assertFalse(lockedExit.isLocked());
    }
}
