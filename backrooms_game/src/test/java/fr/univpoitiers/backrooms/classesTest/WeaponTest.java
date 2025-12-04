package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Entity;
import fr.univpoitiers.backrooms.classes.Hero;
import fr.univpoitiers.backrooms.classes.Locations;
import fr.univpoitiers.backrooms.classes.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    private Locations location;
    private Weapon sword;
    private Entity target;
    private Hero player;

    @BeforeEach
    void setUp() {
        location = new Locations("Test Location", "A test location.");
        sword = new Weapon("Excalibur", 5, "A legendary sword.", 15);
        target = new Hero("Goblin", 100,"goblin", 100, "description", null, location);
        player = new Hero("Player", 100,"hero.", 100, "description", null, location);
    }

    @Test
    void testConstructor() {
        assertEquals("Excalibur", sword.getName());
        assertEquals(5, sword.getVolume());
        assertEquals("A legendary sword.", sword.getDescription());
        assertEquals(15, sword.getDamage());
    }

    @Test
    void testGetDamage() {
        assertEquals(15, sword.getDamage());
    }

    @Test
    void testAttack() {
        int initialHealth = target.getPV();
        sword.attack(target);
        assertEquals(initialHealth - sword.getDamage(), target.getPV());
    }

    @Test
    void testUseWithTarget() {
        int initialHealth = target.getPV();
        String expectedMessage = "You strike " + target.getName() + " with your " + sword.getName() +
                ", dealing " + sword.getDamage() + " damage!";

        String returnedMessage = sword.use(player, target);

        assertEquals(initialHealth - sword.getDamage(), target.getPV(), "Target's health should be reduced.");
        assertEquals(expectedMessage, returnedMessage, "The returned message is not correct.");
    }

    @Test
    void testUseWithoutTarget() {
        String returnedMessage = sword.use(player);
        assertEquals("", returnedMessage);
    }
}