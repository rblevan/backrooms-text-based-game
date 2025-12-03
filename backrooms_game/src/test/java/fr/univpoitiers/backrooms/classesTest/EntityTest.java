package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Entity;
import fr.univpoitiers.backrooms.classes.Locations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private static class ConcreteEntity extends Entity {
        public ConcreteEntity(String name, Locations location) {
            super(name, location);
        }
        public ConcreteEntity(String description, String name, Locations location) {
            super(description, name, location);
        }
        public ConcreteEntity(int PV, String name, int attack, String description, Locations location) {
            super(PV, name, attack, description, location);
        }
    }

    private Locations testLocation;

    @BeforeEach
    void setUp() {
        testLocation = new Locations("- Test Room -", "A room for testing.");
    }

    // --- CONSTRUCTOR TESTS ---

    @Test
    void testConstructorWithTwoArgs_Success() {
        ConcreteEntity entity = new ConcreteEntity("Player", testLocation);
        assertEquals(100, entity.getPV());
        assertEquals(30, entity.getAttack());
        assertEquals("Player", entity.getName());
        assertNotNull(entity.getDescription());
    }

    @Test
    void testConstructorWithTwoArgs_Failure_NullName() {
        try {
            new ConcreteEntity(null, testLocation);
            fail("Expected UnsupportedOperationException was not thrown.");
        } catch (UnsupportedOperationException e) {
            assertEquals("Entity(2) error : Parameter cannot be null", e.getMessage());
        }
    }

    @Test
    void testConstructorWithThreeArgs_Success() {
        ConcreteEntity entity = new ConcreteEntity("A small green creature.", "Goblin", testLocation);
        assertEquals(100, entity.getPV());
        assertEquals(30, entity.getAttack());
        assertEquals("Goblin", entity.getName());
        assertEquals("A small green creature.", entity.getDescription());
    }

    @Test
    void testConstructorWithThreeArgs_Failure_NullDescription() {
        try {
            new ConcreteEntity(null, "Goblin", testLocation);
            fail("Expected UnsupportedOperationException was not thrown.");
        } catch (UnsupportedOperationException e) {
            assertEquals("Entity(3) error : Parameters cannot be null", e.getMessage());
        }
    }

    @Test
    void testConstructorWithFiveArgs_Success() {
        ConcreteEntity entity = new ConcreteEntity(50, "Goblin", 15, "A small green creature.", testLocation);
        assertEquals(50, entity.getPV());
        assertEquals(15, entity.getAttack());
        assertEquals("Goblin", entity.getName());
        assertEquals("A small green creature.", entity.getDescription());
    }

    @Test
    void testConstructorWithFiveArgs_Failure_NegativePV() {
        try {
            new ConcreteEntity(-10, "Ghost", 20, "A spooky ghost.", testLocation);
            fail("Expected UnsupportedOperationException was not thrown for negative PV.");
        } catch (UnsupportedOperationException e) {
            assertEquals("Entity(5) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    @Test
    void testConstructorWithFiveArgs_Failure_NegativeAttack() {
        try {
            new ConcreteEntity(80, "Goblin", -5, "A small green creature.", testLocation);
            fail("Expected UnsupportedOperationException was not thrown for negative attack.");
        } catch (UnsupportedOperationException e) {
            assertEquals("Entity(5) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    // --- SETTER TESTS ---

    @Test
    void testSetPV_Success() {
        ConcreteEntity entity = new ConcreteEntity("Test", testLocation);
        entity.setPV(80);
        assertEquals(80, entity.getPV());
    }

    @Test
    void testSetPV_Failure_NegativeValue() {
        ConcreteEntity entity = new ConcreteEntity("Test", testLocation);
        try {
            entity.setPV(-50);
            fail("Expected UnsupportedOperationException was not thrown for setPV.");
        } catch (UnsupportedOperationException e) {
            assertEquals("PV cannot be negative", e.getMessage());
        }
    }

    @Test
    void testSetAttack_Failure_NegativeValue() {
        ConcreteEntity entity = new ConcreteEntity("Test", testLocation);
        try {
            entity.setAttack(-20);

            fail("Expected UnsupportedOperationException was not thrown for setAttack.");
        } catch (UnsupportedOperationException e) {
            assertEquals("Value attack cannot be negative", e.getMessage());
        }
    }

    @Test
    void testSetName_Failure_NullValue() {
        ConcreteEntity entity = new ConcreteEntity("Test", testLocation);
        try {
            entity.setName(null);
            fail("Expected UnsupportedOperationException was not thrown for setName.");
        } catch (UnsupportedOperationException e) {
            assertEquals("Name cannot be null", e.getMessage());
        }
    }
}
