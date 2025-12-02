package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Backpack;
import fr.univpoitiers.backrooms.classes.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackpackTest {

    @Test
    void testBackpackCreation() {
        Backpack backpack = new Backpack("A standard backpack");
        assertNotNull(backpack);
        assertEquals("Backpack", backpack.getName());
        assertEquals("A standard backpack", backpack.getDescription());
        assertEquals(10, backpack.getVolume());
        assertEquals(100, backpack.getCapacityMax());
        assertEquals(0, backpack.getUsedVolume());
        assertTrue(backpack.isEmpty());
    }

    @Test
    void testAddItem() {
        Backpack backpack = new Backpack("A standard backpack");
        Food apple = new Food("Apple", 1, "A red apple", 10);
        assertTrue(backpack.addItem(apple));
        assertEquals(1, backpack.getUsedVolume());
        assertFalse(backpack.isEmpty());
        assertTrue(backpack.getItems().contains(apple));
    }

    @Test
    void testAddItemExceedingCapacity() {
        Backpack backpack = new Backpack("A standard backpack");
        // Create an item that exceeds the default capacity (100)
        Food largeItem = new Food("Large Item", 101, "Too big", 0);
        assertFalse(backpack.addItem(largeItem));
        assertEquals(0, backpack.getUsedVolume());
        assertTrue(backpack.isEmpty());
    }

    @Test
    void testAddItemFillingCapacity() {
        Backpack backpack = new Backpack("A standard backpack");
        Food item1 = new Food("Item1", 50, "Half capacity", 0);
        Food item2 = new Food("Item2", 50, "Other half capacity", 0);

        assertTrue(backpack.addItem(item1));
        assertEquals(50, backpack.getUsedVolume());
        assertTrue(backpack.addItem(item2));
        assertEquals(100, backpack.getUsedVolume());
        assertEquals(0, backpack.getRemainingCapacity());

        Food item3 = new Food("Item3", 1, "No space left", 0);
        assertFalse(backpack.addItem(item3));
    }

    @Test
    void testRemoveItem() {
        Backpack backpack = new Backpack("A standard backpack");
        Food apple = new Food("Apple", 1, "A red apple", 0);
        backpack.addItem(apple);
        backpack.removeItem(apple);
        assertEquals(0, backpack.getUsedVolume());
    }
}