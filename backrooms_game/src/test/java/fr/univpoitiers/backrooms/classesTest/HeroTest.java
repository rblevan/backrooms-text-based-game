package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Hero;
import fr.univpoitiers.backrooms.classes.Backpack;
import fr.univpoitiers.backrooms.classes.Locations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    private Hero hero;
    private Backpack backpack;
    private Locations location;

    @BeforeEach
    void setUp() {
        backpack = new Backpack("Leather Backpack", "A simple backpack.", 15);
        location = new Locations("Starting Room", "You are in a small, dimly lit room.");
        hero = new Hero("Aragorn", 100, "Aragorn", 25, "A ranger of the North.", backpack, location);
    }

    @Test
    void testConstructor() {
        assertEquals("Aragorn", hero.getUsername());
        assertEquals(100, hero.getPV());
        assertEquals("Aragorn", hero.getName());
        assertEquals(25, hero.getAttack());
        assertEquals("A ranger of the North.", hero.getDescription());
        assertEquals(backpack, hero.getBackpack());
        assertEquals(location, hero.getLocation());
    }

    @Test
    void testGetBackpack() {
        assertNotNull(hero.getBackpack());
        assertEquals(backpack, hero.getBackpack());
    }

    @Test
    void testGetUsername() {
        assertEquals("Aragorn", hero.getUsername());
    }

    @Test
    void testGetLocation() {
        assertNotNull(hero.getLocation());
        assertEquals(location, hero.getLocation());
    }
}
