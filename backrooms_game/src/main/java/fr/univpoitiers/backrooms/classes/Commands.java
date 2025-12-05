package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

import javax.swing.*;
import java.util.*;

/**
 * Helper class that holds the list of all valid command words.
 * It is used to validate user input before processing.
 */
class CommandWords {
    private static final String[] VALID_COMMANDS = {
            "GO", "HELP", "LOOK", "ATTACK", "TAKE", "USE", "QUIT", "HEALTH", "ME"
    };

    public static List<String> getValidCommands() {
        return Arrays.asList(VALID_COMMANDS);
    }

    public static boolean isCommand(String aString) {
        for (String command : VALID_COMMANDS) {
            if (command.equals(aString.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}

/**
 * This class is the main control logic for the game.
 * It parses user input, executes the corresponding commands, and modifies the game state.
 * It acts as the controller between the User Interface and the Model (Hero, Locations, Items).
 */
public class Commands {
    private final String commandList = String.join(" ", CommandWords.getValidCommands());

    private Hero player;
    private Locations currentLocation;

    /**
     * Initializes the command processor.
     *
     * @param player           The player (Hero) controlled by these commands.
     * @param startingLocation The initial location of the player.
     */
    public Commands(Hero player, Locations startingLocation) {
        this.player = player;
        this.currentLocation = startingLocation;
    }

    /**
     * Main method to process a line of input typed by the user.
     * It parses the input into a command word and arguments, checks if the command is valid,
     * and dispatches the execution to the specific method (e.g., go, look, take).
     *
     * @param input The raw string entered by the user.
     * @return A string containing the game's response to be displayed in the UI.
     */
    public String processCommand(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            return "";
        }

        String[] parts = trimmedInput.toUpperCase().split("\\s+", 2);
        String commandWord = parts[0];
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        if (!CommandWords.isCommand(commandWord)) {
            return "Unknown command. Type HELP for a list of commands.";
        }

        // Execute the base command
        switch (commandWord) {
            case "GO":
                return go(args);
            case "HELP":
                return help();
            case "LOOK":
                return look(args);
            case "ATTACK":
                return attack(args);
            case "TAKE":
                return take(args);
            case "USE":
                return use(args);
            case "QUIT":
                return quit();
            case "HEALTH":
                return health();
            case "ME":
                return me();
            default:
                return "Internal execution error.";
        }
    }

    /**
     * Handles the "GO" command.
     * Moves the player to a neighboring room if the direction is valid and an exit exists.
     *
     * @param args The arguments of the command (expected: [direction]).
     * @return A message describing the result of the movement or an error message.
     */
    private String go(String[] args) {
        if (args.length == 0) {

            return "Go where? Usage: GO [direction]\n The directions are: NORTH, SOUTH, EAST, WEST.";
        }
        Direction direction = Direction.valueOf(args[0]);
        if (this.currentLocation.getExits().containsKey(direction)){
            this.currentLocation = this.currentLocation.getExits().get(direction).getDestination();
            this.player.setLocation(this.currentLocation);
         return "You go " + direction + ".";
        }
        else {
            return "You can't go that way. Try another direction. (NORTH, SOUTH, EAST, WEST)";
        }
    }

    /**
     * Handles the "HELP" command.
     * Displays a list of all valid command verbs to assist the player.
     *
     * @return A string listing available commands.
     */
    private String help() {
        return "Available commands are: " + commandList;
    }

    /**
     * Handles the "LOOK" command.
     * Contextual behavior:
     * 1. No arguments: Describes the current room, visible items, and characters.
     * 2. "backpack": Lists items in the player's inventory.
     * 3. [item name]: Inspects a specific item in the room or in the backpack.
     *
     * @param args The arguments (optional).
     * @return The description requested.
     */
    private String look(String[] args) {

        // --- 1. without argument : complete description ---
        if (args.length == 0) {

            String description_location = this.currentLocation.getDescription();
            Map<String, Items> items = this.currentLocation.getItems();
            Map<String, Characters> characters = this.currentLocation.getCharacters();

            String itemsDescription;
            String charactersDescription;

            // Display of the items
            if (!items.isEmpty()) {
                List<String> itemNames = new ArrayList<>();
                List<Integer> itemVolumes = new ArrayList<>();
                for (Items item : items.values()) {
                    itemNames.add(item.getName());
                    itemVolumes.add(item.getVolume());
                }
                itemsDescription = "You see the following items: " + String.join(", ", itemNames) + ". Their volumes are : " + itemVolumes.toString() + ".";

            } else {
                itemsDescription = "There are no notable items here.";
            }

            // Display of the characters
            if (!characters.isEmpty()) {
                List<String> characterNames = new ArrayList<>();
                for (Characters character : characters.values()) {
                    characterNames.add(character.getName());
                }
                charactersDescription = "You see the following characters: " + String.join(", ", characterNames) + ".";
            } else {
                charactersDescription = "There are no notable characters here.";
            }

            return description_location + "\n" + itemsDescription + "\n" + charactersDescription;
        }
        // --- 2. LOOK [item] : display a specific item ---
        String objectToLookAtName = args[0];
        Items targetItem = null;

        if (objectToLookAtName.equalsIgnoreCase("backpack")) {
            List<Items> items = this.player.getBackpack().getItems();

            if (items.isEmpty()) {
                return "Your backpack is empty.";
            }

            List<String> itemNames = new ArrayList<>();
            for (Items item : items) {
                itemNames.add(item.getName());
            }

            return "Inside your backpack, you see: " + String.join(", ", itemNames) + ". Your backpack is currently using " + this.player.getBackpack().getUsedVolume() + " out of " + this.player.getBackpack().getCapacityMax() + " units of space.";
        }

        // serach in the location
        targetItem = this.currentLocation.getItemByName(objectToLookAtName);

        // else, search in the player's backpack
        if (targetItem == null) {
            targetItem = this.player.getBackpack().getItemByName(objectToLookAtName);
        }

        // if found
        if (targetItem != null) {
            return targetItem.getDescription() + "\nThe volume of this item is " + targetItem.getVolume() + " units.";
        }

        // no item found
        return "You don't see " + objectToLookAtName + " here or in your backpack.";
    }

    /**
     * Handles the "ATTACK" command.
     * Allows the player to attack a character using a weapon from their inventory.
     *
     * @param args The arguments (expected: [weapon name] [target name]).
     * @return A message describing the outcome of the attack.
     */
    private String attack(String[] args) {
        if (args.length < 2) {
            return "Attack what with what? Usage: ATTACK [weapon] [target]";
        }

        String weaponName = args[0];
        String targetName = args[1];

        // search the weapon in the player's backpack
        Items item = player.getBackpack().getItemByName(weaponName);
        if (item == null) {
            return "You don't have a " + weaponName + ".";
        }

        // verify that the item is a weapon
        if (!(item instanceof Weapon)) {
            return weaponName + " is not a weapon!";
        }

        // search the target in the current location
        Entity target = currentLocation.getCharacterByName(targetName);
        if (target == null) {
            return "There is no " + targetName + " here.";
        }

        // --- simple message to display ---
        return ((Weapon) item).use(player, target);
    }

    /**
     * Handles the "TAKE" command.
     * Moves an item from the current room to the player's backpack.
     * Checks if the item exists and if the backpack has enough capacity.
     *
     * @param args The arguments (expected: [item name]).
     * @return A success message or an error (e.g., "Backpack is full").
     */
    private String take(String[] args) {
        //no argument, return error message
        if (args.length == 0) {
            return "Take what? Usage: TAKE [item]";
        }
        String itemName = args[0];
        Items itemToTake = currentLocation.getItemByName(itemName);
        //item not found
        if (itemToTake == null) {
            return "There is no " + itemName + " here.";
        }
        //take the item
        if (player.getBackpack().addItem(itemToTake)) {
            currentLocation.removeItem(itemToTake);
            return "You take the " + itemToTake.getName() + ".";
        }
        //if the backpack is full
        else {
            return "Your backpack is full.";
        }
    }

    /**
     * Handles the "USE" command.
     * Uses an item from the inventory.
     * - Food: Consumed immediately to heal the player.
     * - Spells: Requires a target argument to be cast.
     *
     * @param args The arguments (expected: [item name] or [item name] [target]).
     * @return The result of the item usage.
     */
    private String use(String[] args) {
        //no argument, return error message
        if (args.length == 0) {
            return "Use what? Usage: USE [item] or USE [item] [target]";
        }

        String itemName = args[0];
        Items item = player.getBackpack().getItemByName(itemName);

        if (item == null) {
            return "You don't have a " + itemName + ".";
        }

        // --- Food : use it alone ---
        if (item instanceof Food) {
            return ((Food) item).use(player); // Heal + remove item from backpack
        }

        // --- other items need a target ---
        if (args.length < 2) {
            return "This item requires a target. Usage: USE [item] [target]";
        }

        String targetName = args[1];
        Entity target = currentLocation.getCharacterByName(targetName);

        if (target == null) {
            return "There is no " + targetName + " here.";
        }

        // --- Spells : spell on target---
        return ((Spells) item).use(player, target);
    }

    /**
     * Handles the "QUIT" command.
     *
     * @return A special signal string "QUIT_GAME" intercepted by the UI to close the window.
     */
    private String quit() {
        return "QUIT_GAME";
    }

    /**
     * Handles the "HEALTH" command.
     * Provides a descriptive status of the player's current Hit Points (PV).
     *
     * @return A status message describing the player's physical condition.
     */
    private String health(){
        int hp = player.getPV();
        int maxHp = player.getMax_hp();


        if (hp == maxHp) {
            return "You feel fully healthy. No need to heal for now.";
        } else if (hp > maxHp / 2) {
            return "You have some scratches, but nothing serious. (" + hp + "/" + maxHp + ")";
        } else {
            return "You're badly injured! You should heal soon. (" + hp + "/" + maxHp + ")";
        }
    }

    /**
     * Handles the "ME" command.
     * Provides information about the player's name and description.
     *
     * @return A message describing the player's identity.
     */
    private String me(){
        return "your name is " + player.getName() + " and " + player.getDescription();
    }
}
