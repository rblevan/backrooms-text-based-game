package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    @Test
    void testWeaponCreation() {
        Weapon weapon = new Weapon("Sword", 5, 20);
        assertNotNull(weapon);
        assertEquals("Sword", weapon.getName());
        assertEquals(5, weapon.getVolume());
        assertEquals(20, weapon.getDamage());
    }

    @Test
    void testWeaponCreationWithInvalidName() {
        try {
            new Weapon(null, 5, 20);
        } catch (UnsupportedOperationException e) {
            assertEquals("Weapon(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    @Test
    void testWeaponCreationWithNegativeVolume() {
        try {
            new Weapon("Sword", -5, 20);
        } catch (UnsupportedOperationException e) {
            assertEquals("Weapon(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    @Test
    void testWeaponCreationWithNegativeDamage() {
        try {
            new Weapon("Sword", 5, -20);
        } catch (UnsupportedOperationException e) {
            assertEquals("Weapon(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }
}