package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Concrete subclass for testing abstract Entity class
class ConcreteEntity extends Entity {
    public ConcreteEntity(String description, String name) {
        super(description, name);
    }

    public ConcreteEntity(int PV, String name, int attack, String description) {
        super(PV, name, attack, description);
    }
}

class EntityTest {

    private Entity entity1;
    private Entity entity2;

    @BeforeEach
    void setUp() {
        entity1 = new ConcreteEntity("A brave warrior", "Hero");
        entity2 = new ConcreteEntity(150, "Dragon", 40, "A fearsome dragon");
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(100, entity1.getPV());
        assertEquals("Hero", entity1.getName());
        assertEquals(30, entity1.getAttack());
        assertEquals("A brave warrior", entity1.getDescription());
        assertEquals(100, entity1.getMax_hp());
    }

    @Test
    void testFullConstructor() {
        assertEquals(150, entity2.getPV());
        assertEquals("Dragon", entity2.getName());
        assertEquals(40, entity2.getAttack());
        assertEquals("A fearsome dragon", entity2.getDescription());
        assertEquals(150, entity2.getMax_hp());
    }

    @Test
    void testSettersAndGetters() {
        entity1.setPV(80);
        assertEquals(80, entity1.getPV());

        entity1.setName("NewHero");
        assertEquals("NewHero", entity1.getName());

        entity1.setAttack(35);
        assertEquals(35, entity1.getAttack());

        entity1.setDescription("An even braver warrior");
        assertEquals("An even braver warrior", entity1.getDescription());
    }

    @Test
    void testTakeDamage() {
        int initialPV = entity2.getPV();
        entity2.takeDamage(20);
        assertEquals(initialPV - 20, entity2.getPV());
    }

    @Test
    void testSetNegativeValues() {
        assertThrows(UnsupportedOperationException.class, () -> entity1.setPV(-10));
        assertThrows(UnsupportedOperationException.class, () -> entity1.setAttack(-5));
    }

    @Test
    void testSetNullValues() {
        assertThrows(UnsupportedOperationException.class, () -> entity1.setName(null));
        assertThrows(UnsupportedOperationException.class, () -> entity1.setDescription(null));
    }

    @Test
    void testConstructorWithInvalidValues() {
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteEntity(-100, "Invalid", 20, "Invalid desc"));
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteEntity(100, "Invalid", -20, "Invalid desc"));
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteEntity(null, "Invalid"));
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteEntity("Invalid desc", null));
    }
}
