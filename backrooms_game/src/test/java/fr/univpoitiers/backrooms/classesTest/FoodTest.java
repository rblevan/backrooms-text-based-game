package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    void testFoodCreation() {
        Food food = new Food("Apple", 1, "A red apple", 10);
        assertNotNull(food);
        assertEquals("Apple", food.getName());
        assertEquals(1, food.getVolume());
        assertEquals("A red apple", food.getDescription());
        assertEquals(10, food.getHealthPoints());
    }

    @Test
    void testFoodCreationWithNullName() {
        try {
            Food food = new Food(null, 1, "A red apple", 10);
        } catch (UnsupportedOperationException e) {
            assertEquals("Food(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    @Test
    void testFoodCreationWithNegativeVolume() {
        try {
            Food food = new Food("Apple", -1, "A red apple", 10);
        } catch (UnsupportedOperationException e) {
            assertEquals("Food(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    @Test
    void testFoodCreationWithNullDescription() {
        try {
            Food food = new Food("Apple", 1, null, 10);
        } catch (UnsupportedOperationException e) {
            assertEquals("Food(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

    @Test
    void testFoodCreationWithNegativeHealthPoints() {
        try {
            Food food = new Food("Apple", 1, "A red apple", -10);
        } catch (UnsupportedOperationException e) {
            assertEquals("Food(4) error : Parameters cannot be null or negative", e.getMessage());
        }
    }

}