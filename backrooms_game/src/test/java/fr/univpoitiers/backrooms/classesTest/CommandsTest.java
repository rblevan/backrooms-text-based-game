package fr.univpoitiers.backrooms.classesTest;

import fr.univpoitiers.backrooms.classes.*;
import fr.univpoitiers.backrooms.enumeration.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    private Commands commands;
    private Hero player;
    private Locations room1;
    private Locations room2;
    private Characters goblin;
    private Weapon sword;
    private Food apple;

    @BeforeEach
    void setUp() {
        // Create locations
        room1 = new Locations("Room 1", "You are in a dusty room.");
        room2 = new Locations("Room 2", "You are in a clean room.");
        room1.addExit(Direction.NORTH, new Exits(room2, "A wooden door.", false));

        // Create items
        sword = new Weapon("Sword", 5, "A sharp sword.", 10);
        apple = new Food("Apple", 1, "A red apple.", 20);
        room1.addItem(sword);

        // Create characters
        goblin = new Characters(20,"Goblin", 50, "A small goblin.");
        room1.addCharacter(goblin);

        // Create player
        Backpack backpack = new Backpack("Player's Backpack", "", 20);
        player = new Hero("Player1", 80, "Hero", 20, "A brave adventurer.", backpack, room1);

        // Initialize Commands
        commands = new Commands(player, room1);
    }

    @Test
    void testGoCommand_Success() {
        String result = commands.processCommand("GO NORTH");
        assertEquals("You go NORTH.", result);
        assertEquals(room2, player.getLocation());
    }

    @Test
    void testGoCommand_Fail() {
        String result = commands.processCommand("GO SOUTH");
        assertTrue(result.contains("You can't go that way."));
        assertEquals(room1, player.getLocation());
    }

    @Test
    void testHelpCommand() {
        String result = commands.processCommand("HELP");
        assertTrue(result.contains("Available commands are:"));
        assertTrue(result.contains("GO"));
        assertTrue(result.contains("QUIT"));
    }

    @Test
    void testLookCommand_Room() {
        String result = commands.processCommand("LOOK");
        assertTrue(result.contains(room1.getDescription()));
        assertTrue(result.contains("You see the following items: Sword"));
        assertTrue(result.contains("You see the following characters: Goblin"));
    }

    @Test
    void testTakeCommand_Success() {
        String result = commands.processCommand("TAKE Sword");
        assertEquals("You take the Sword.", result);
        assertNotNull(player.getBackpack().getItemByName("Sword"));
        assertNull(room1.getItemByName("Sword"));
    }

    @Test
    void testTakeCommand_Fail_NotFound() {
        String result = commands.processCommand("TAKE Shield");
        assertEquals("There is no SHIELD here.", result);
    }

    @Test
    void testAttackCommand_Success() {
        player.getBackpack().addItem(sword); // Player needs the weapon first
        int goblinInitialHealth = goblin.getPV();
        String result = commands.processCommand("ATTACK Sword Goblin");
        assertTrue(result.contains("You strike Goblin with your Sword"));
        assertEquals(goblinInitialHealth - sword.getDamage(), goblin.getPV());
    }

    @Test
    void testAttackCommand_Fail_NoWeaponInBag() {
        String result = commands.processCommand("ATTACK Sword Goblin");
        assertEquals("You don't have a SWORD.", result);
    }

    @Test
    void testUseCommand_Food() {
        player.getBackpack().addItem(apple);
        int playerInitialHealth = player.getPV();
        String result = commands.processCommand("USE Apple");
        assertTrue(result.contains("has been healed by " + apple.getHealPoints()));
        assertEquals(playerInitialHealth + apple.getHealPoints(), player.getPV());
        assertNull(player.getBackpack().getItemByName("Apple")); // Food is consumed
    }

    @Test
    void testQuitCommand() {
        String result = commands.processCommand("QUIT");
        assertEquals("QUIT_GAME", result);
    }

    @Test
    void testHealthCommand() {
        player.setPV(90);
        String result1 = commands.processCommand("HEALTH");
        assertTrue(result1.contains("some scratches"));

        player.setPV(30);
        String result2 = commands.processCommand("HEALTH");
        assertTrue(result2.contains("badly injured"));

        player.setPV(player.getMax_hp());
        String result3 = commands.processCommand("HEALTH");
        assertTrue(result3.contains("fully healthy"));
    }

    @Test
    void testMeCommand() {
        String result = commands.processCommand("ME");
        assertTrue(result.contains(player.getName()));
        assertTrue(result.contains(player.getDescription()));
    }

    @Test
    void testUnknownCommand() {
        String result = commands.processCommand("JUMP");
        assertEquals("Unknown command. Type HELP for a list of commands.", result);
    }
}
