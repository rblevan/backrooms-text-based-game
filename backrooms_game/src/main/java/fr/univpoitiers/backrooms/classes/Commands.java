package fr.univpoitiers.backrooms.classes;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

class CommandWords {
    private static final String[] VALID_COMMANDS = {
            "GO", "HELP", "LOOK", "ATTACK", "TAKE", "USE", "QUIT"
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

public class Commands {
    private final String commandList = String.join(" ", CommandWords.getValidCommands());

    private Hero player;
    private Locations currentLocation;


    public Commands(Hero player, Locations startingLocation) {
        this.player = player;
        this.currentLocation = startingLocation;
    }

    public String processCommand(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            return "";
        }

        String[] parts = trimmedInput.toUpperCase().split("\\s+", 2);
        String commandWord = parts[0];
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        // Check if it's a known command
        if (!CommandWords.isCommand(commandWord)) {
            // Check if it's a special ability the player has learned
            return "Unknown command. Type HELP for a list of commands.";
        }

        switch (commandWord) {
            case "GO":
                return go(args);
            case "HELP":
                return help(args);
            case "LOOK":
                return look(args);
            case "ATTACK":
                return attack(args);
            case "TAKE":
                return take(args);
            case "USE":
                return use(args);
            case "QUIT":
                return quit(args);
            default:
                return "Internal execution error.";
        }
    }

    private String go(String[] args) {
        if (args.length == 0) {
            return "Go where? Specify a direction or a place.";
        }
        String direction = args[0];
        // TODO: Implement logic to find the next location based on 'direction'
        // and update this.currentLocation.
        return "You try to go " + direction + "... (Not implemented)";
    }

    private String help(String[] args) {
        return "Available commands are: " + commandList;
    }

    private String look(String[] args) {
        if (args.length == 0) {
            // Now this works because 'currentLocation' is an object.
            return this.currentLocation.getDescription();
        }
        String objectToLookAt = args[0];
        // TODO: Implement logic to find 'objectToLookAt' in the current room
        // or in the player's inventory and return its description.
        return "You look at " + objectToLookAt + "... (Not implemented)";
    }

    private String attack(String[] args) {
        if (args.length == 0) {
            return "Attack what?";
        }
        String targetName = args[0];
        // TODO: Find the entity named 'targetName' in the current room.
        // Then, use the player's equipped weapon to attack it.
        // Example: player.attack(target);
        return "You attack " + targetName + "! (Not implemented)";
    }

    private String take(String[] args) {
        if (args.length == 0) {
            return "Take what?";
        }
        String itemName = args[0];
        // TODO: Find the item 'itemName' in the current room.
        // Then, add it to the player's backpack.
        // Example: player.getBackpack().addItem(item);
        return "You try to take " + itemName + ". (Not implemented)";
    }

    private String use(String[] args) {
        if (args.length == 0) {
            return "Use what?";
        }
        String itemName = args[0];
        // TODO: Find the item 'itemName' in the player's backpack.
        // Then, check its type (Food, Weapon, etc.) and apply its effect.
        // Example: if (item instanceof Food) { player.heal(((Food)item).getHealPoints()); }
        return "You try to use " + itemName + ". (Not implemented)";
    }

    private String quit(String[] args) {
        // This command should probably signal the main game loop to exit.
        // For now, we can just return a message. A better way is to have a special return value.
        // We will use "QUIT_GAME" as a special signal.
        return "QUIT_GAME";
    }
}
