package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Entity;
import fr.univpoitiers.backrooms.classes.Hero;
import fr.univpoitiers.backrooms.classes.Locations;
import fr.univpoitiers.backrooms.classes.Spells;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellsTest {

    private Locations location;
    private Spells fireball;
    private Hero player;
    private Entity target;

    @BeforeEach
    void setUp() {
        fireball = new Spells("Fireball", 1, "A fiery projectile.");
        player = new Hero("Mage", 100,"mage", 100, "A powerful mage.", null, location);
        target = new Hero("Orc", 20,"Orc", 120, "A menacing orc.", null, location);
    }

    @Test
    void testConstructor() {
        assertEquals("Fireball", fireball.getName());
        assertEquals(1, fireball.getVolume());
        assertEquals("A fiery projectile.", fireball.getDescription());

        assertNotNull(fireball);
    }

    @Test
    void testCast() {
        String expectedMessage = "You cast Fireball. A fiery projectile.";
        String actualMessage = fireball.cast(player);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testUseWithTarget() {
        String expectedMessage = "You cast Fireball. A fiery projectile. on Orc";
        String actualMessage = fireball.use(player, target);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testUseWithoutTarget() {
        // This method should return an empty string as per its implementation.
        String actualMessage = fireball.use(player);
        assertEquals("", actualMessage);
    }
}