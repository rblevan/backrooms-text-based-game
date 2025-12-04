package fr.univpoitiers.backrooms.classesTest;
    // Test for the Backpack class
    // Test for the constructor
    // Test for the addItem method
    // Test for the removeItem method

    // Test for the getItemByName method
    // Test for the getItems method
    // Test for the getUsedVolume method
    // Test for the getCapacityMax method
    // Test for the getDescription method
    // Test for the getName method
    // Test for the getVolume method

import fr.univpoitiers.backrooms.classes.Backpack;
import fr.univpoitiers.backrooms.classes.Items;
import fr.univpoitiers.backrooms.classes.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackpackTest {

    private Backpack backpack;
    private Items item1;
    private Items item2;
    private Items item3;

    @BeforeEach
    void setUp() {
        backpack = new Backpack("Test Backpack", "A backpack for testing", 10);
        item1 = new Weapon("Sword", 5, "A sharp sword", 5);
        item2 = new Weapon("Shield", 3, "A sturdy shield", 0);
        item3 = new Weapon("Helmet", 4, "A protective helmet", 0);
    }

    @Test
    void testConstructor() {
        assertEquals("Test Backpack", backpack.getName());
        assertEquals("A backpack for testing", backpack.getDescription());
        assertEquals(10, backpack.getCapacityMax());
        assertEquals(0, backpack.getUsedVolume());
        assertTrue(backpack.getItems().isEmpty());
    }

    @Test
    void testAddItem_success() {
        assertTrue(backpack.addItem(item1));
        assertEquals(5, backpack.getUsedVolume());
        assertTrue(backpack.getItems().contains(item1));
    }

    @Test
    void testAddItem_fullBackpack() {
        backpack.addItem(item1);
        backpack.addItem(item2);
        backpack.addItem(item3);
        assertEquals(8, backpack.getUsedVolume());
        assertFalse(backpack.getItems().contains(item3));
    }

    @Test
    void testRemoveItem() {
        backpack.addItem(item1);
        backpack.addItem(item2);
        backpack.removeItem(item1);
        assertEquals(3, backpack.getUsedVolume());
        assertFalse(backpack.getItems().contains(item1));
    }

    @Test
    void testGetItemByName() {
        backpack.addItem(item1);
        backpack.addItem(item2);
        assertEquals(item1, backpack.getItemByName("Sword"));
        assertEquals(item2, backpack.getItemByName("Shield"));
    }

    @Test
    void testGetItems() {
        backpack.addItem(item1);
        backpack.addItem(item2);
        assertEquals(2, backpack.getItems().size());
        assertTrue(backpack.getItems().contains(item1));
        assertTrue(backpack.getItems().contains(item2));
    }

    @Test
    void testGetUsedVolume() {
        backpack.addItem(item1);
        backpack.addItem(item2);
        assertEquals(8, backpack.getUsedVolume());
    }

    @Test
    void testGetCapacityMax() {
        assertEquals(10, backpack.getCapacityMax());
    }
}