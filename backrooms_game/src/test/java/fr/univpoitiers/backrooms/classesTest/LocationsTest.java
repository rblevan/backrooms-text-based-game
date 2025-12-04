package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Characters;
import fr.univpoitiers.backrooms.classes.Exits;
import fr.univpoitiers.backrooms.classes.Items;
import fr.univpoitiers.backrooms.classes.Locations;
import fr.univpoitiers.backrooms.classes.Weapon;
import fr.univpoitiers.backrooms.enumeration.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationsTest {

    private Locations location;
    private Items sword;
    private Characters goblin;
    private Locations nextLocation;
    private Exits exit;

    @BeforeEach
    void setUp() {
        location = new Locations("The Grand Hall", "A vast and empty hall.");
        sword = new Weapon("Longsword", 4, "A sharp steel longsword.", 12);
        goblin = new Characters(100,"Grizelda", 50, "A grumpy goblin.");
        nextLocation = new Locations("The Armory", "A room full of weapons.");
        exit = new Exits(nextLocation, "north", false);
    }

    @Test
    void testConstructor() {
        assertEquals("The Grand Hall", location.getTitle());
        assertEquals("A vast and empty hall.", location.getDescription());
        assertTrue(location.getExits().isEmpty());
        assertTrue(location.getItems().isEmpty());
        assertTrue(location.getCharacters().isEmpty());
    }

    @Test
    void testAddItemAndGetItemByName() {
        location.addItem(sword);
        assertFalse(location.getItems().isEmpty());
        assertEquals(sword, location.getItemByName("Longsword"));
        assertEquals(sword, location.getItemByName("longsword")); // Test case-insensitivity
        assertNull(location.getItemByName("NonExistentItem"));
    }

    @Test
    void testRemoveItem() {
        location.addItem(sword);
        assertFalse(location.getItems().isEmpty());
        location.removeItem(sword);
        assertTrue(location.getItems().isEmpty());
        assertNull(location.getItemByName("Longsword"));
    }

    @Test
    void testAddCharacterAndGetCharacterByName() {
        location.addCharacter(goblin);
        assertFalse(location.getCharacters().isEmpty());
        assertEquals(goblin, location.getCharacterByName("Grizelda"));
        assertEquals(goblin, location.getCharacterByName("grizelda")); // Test case-insensitivity
        assertNull(location.getCharacterByName("NonExistentCharacter"));
    }

    @Test
    void testRemoveCharacter() {
        location.addCharacter(goblin);
        assertFalse(location.getCharacters().isEmpty());
        location.removeCharacter(goblin);
        assertTrue(location.getCharacters().isEmpty());
        assertNull(location.getCharacterByName("Grizelda"));
    }

    @Test
    void testAddAndGetExit() {
        location.addExit(Direction.NORTH, exit);
        assertFalse(location.getExits().isEmpty());
        assertEquals(exit, location.getExits().get(Direction.NORTH));
        assertEquals(nextLocation, location.getExits().get(Direction.NORTH).getDestination());
    }
}