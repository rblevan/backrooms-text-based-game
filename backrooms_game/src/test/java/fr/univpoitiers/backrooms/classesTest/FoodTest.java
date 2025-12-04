package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Food;
import fr.univpoitiers.backrooms.classes.Hero;
import fr.univpoitiers.backrooms.classes.Backpack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    private Food apple;
    private Hero player;

    @BeforeEach
    void setUp() {
        apple = new Food("Apple", 1, "A juicy red apple.", 20);
        Backpack backpack = new Backpack("Backpack", "A backpack for testing", 10);
        player = new Hero("Player", 100, "Hero", 100, "A powerful hero.", backpack, null);
        backpack.addItem(apple);
    }

    @Test
    void testConstructor() {
        assertEquals("Apple", apple.getName());
        assertEquals(1, apple.getVolume());
        assertEquals("A juicy red apple.", apple.getDescription());
        assertEquals(20, apple.getHealPoints());
    }

    @Test
    void testGetHealPoints() {
        assertEquals(20, apple.getHealPoints());
    }

    @Test
    void testUse() {
        int initialPV = player.getPV();
        String expectedMessage = player.getName() + " has been healed by " + apple.getHealPoints() + " points.";
        
        String message = apple.use(player);

        assertEquals(initialPV + apple.getHealPoints(), player.getPV());
        assertNull(player.getBackpack().getItemByName("Apple")); // Item should be removed
        assertEquals(expectedMessage, message);
    }

    @Test
    void testUseOnTarget() {
        // This method should do nothing and return an empty string
        String message = apple.use(player, new Hero("Target", 100,"target",10,"A target." , null, null));
        assertEquals("", message);
    }
}
