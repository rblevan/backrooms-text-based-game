package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Items;
import fr.univpoitiers.backrooms.classes.Hero;
import fr.univpoitiers.backrooms.classes.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Concrete subclass for testing abstract Items class
class ConcreteItem extends Items {
    public ConcreteItem(String name, int volume, String description) {
        super(name, volume, description);
    }

    @Override
    public String use(Hero player) {
        return "Used on player";
    }

    @Override
    public String use(Hero player, Entity target) {
        return "Used on target";
    }
}

class ItemsTest {

    private Items item;

    @BeforeEach
    void setUp() {
        item = new ConcreteItem("Generic Item", 5, "A standard item for testing.");
    }

    @Test
    void testConstructor() {
        assertEquals("Generic Item", item.getName());
        assertEquals(5, item.getVolume());
        assertEquals("A standard item for testing.", item.getDescription());
    }

    @Test
    void testConstructorWithInvalidValues() {
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteItem(null, 5, "description"));
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteItem("name", -1, "description"));
        assertThrows(UnsupportedOperationException.class, () -> new ConcreteItem("name", 5, null));
    }

    @Test
    void testGetName() {
        assertEquals("Generic Item", item.getName());
    }

    @Test
    void testGetVolume() {
        assertEquals(5, item.getVolume());
    }

    @Test
    void testGetDescription() {
        assertEquals("A standard item for testing.", item.getDescription());
    }

    @Test
    void testAbstractUseMethods() {
        // These tests just confirm that the abstract methods can be called on a concrete instance.
        Hero dummyHero = new Hero("Dummy", 100, "Dummy", 10, "A dummy hero.", null, null);
        Entity dummyTarget = new Hero("Target", 100, "Target", 10, "A dummy target.", null, null);
        
        assertEquals("Used on player", item.use(dummyHero));
        assertEquals("Used on target", item.use(dummyHero, dummyTarget));
    }
}
