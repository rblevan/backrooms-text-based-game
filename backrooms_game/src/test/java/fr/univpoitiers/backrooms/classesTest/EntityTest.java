package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Entity;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class EntityTest {

    private static class ConcreteEntity extends Entity {
        public ConcreteEntity(String description, String name) {
            super(description, name);
        }

        public ConcreteEntity(int PV, String name, int attack, String description) {
            super(PV, name, attack, description);
        }
    }

    @Test
    void testForConstructorWithTwoArgs() {
        ConcreteEntity entity = new ConcreteEntity("This is a test entity", "TestEntityThis is a test entity");
        assertEquals(100, entity.getPV());
        assertEquals(30, entity.getAttack());
    }

    @Test
    void testForConstructorWithFourArgs() {
        ConcreteEntity entity = new ConcreteEntity(50, "TestEntity", 20, "This is a test entity");
        assertEquals(50, entity.getPV());
        assertEquals(20, entity.getAttack());
    }

    @Test
    void testForSetters() {
        ConcreteEntity entity = new ConcreteEntity("This is a test entity", "TestEntity");
        entity.setPV(75);
        entity.setAttack(40);
        assertEquals(75, entity.getPV());
        assertEquals(40, entity.getAttack());
    }

    @Test
    void testForSettersWithNegativeValues() {
        ConcreteEntity entity = new ConcreteEntity("This is a test entity", "TestEntity");
        try {
            entity.setPV(-50);
            entity.setAttack(-20);
        } catch (UnsupportedOperationException e) {
            assertEquals("PV cannot be negative", e.getMessage());
            assertEquals("Value attack cannot be negative", e.getMessage());
        }
    }

    @Test
    void testForSettersWithNullValues() {
        ConcreteEntity entity = new ConcreteEntity("This is a test entity", "TestEntity");
        try {
            entity.setDescription(null);
        } catch (UnsupportedOperationException e) {
            assertEquals("Description cannot be null", e.getMessage());
        }
        try {
            entity.setName(null);
        } catch (UnsupportedOperationException e) {
            assertEquals("Name cannot be null", e.getMessage());
        }
    }

    @Test
    void testForGetters() {
        ConcreteEntity entity = new ConcreteEntity("This is a test entity", "TestEntity");
        assertEquals("TestEntity", entity.getName());
        assertEquals("This is a test entity", entity.getDescription());
    }

}
