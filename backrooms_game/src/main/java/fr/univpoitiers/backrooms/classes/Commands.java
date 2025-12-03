package fr.univpoitiers.backrooms.classes;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
            default:
                return "Internal execution error.";
        }
    }

    private String go(String[] args) {
        if (args.length == 0) {
            return "Go where? Specify a direction or a place.";
        }
        String direction = args[0];
        if (this.currentLocation.getExits().containsKey(direction)){
            this.currentLocation = this.currentLocation.getExits().get(direction);
         return "You go " + direction + ".";
        }
        else{
            return "You can't go that way. Try another direction. (NORTH, SOUTH, EAST, WEST, UP, DOWN)";
        }
    }

    private String help() {
        return "Available commands are: " + commandList;
    }

    private String look(String[] args) {
    if (args.length == 0) {
        String description = this.currentLocation.getDescription();
        List<Items> items = this.currentLocation.getItems();

        String itemsDescription = "";
        if (!items.isEmpty()) {
            List<String> itemNames = new ArrayList<>();
            for (Items item : items) {
                itemNames.add(item.getName());
            }
            itemsDescription = "You can also see: " + String.join(", ", itemNames) + ".";
        } else {
            itemsDescription = "There are no notable objects here.";
        }

        return description + "\n" + itemsDescription;
    }

    // Regarder un objet spécifique (LOOK [objet])
    String objectToLookAtName = args[0];
    Items targetItem = null;

    // Chercher dans l'emplacement actuel
    targetItem = this.currentLocation.getItemByName(objectToLookAtName);

    // Si pas trouvé, chercher dans l'inventaire du joueur
    if (targetItem == null) {
        // Cette méthode doit être implémentée dans la classe de l'inventaire/sac à dos
        targetItem = this.player.getBackpack().getItemByName(objectToLookAtName);
    }

    // Si l'objet est trouvé, utiliser sa méthode getDescription()
    if (targetItem != null) {
        return targetItem.getDescription();
    } else {
        return "You don't see " + objectToLookAtName + " here or in your backpack.";
    }
}

    private String attack(String[] args) {
        if (args.length == 0) {
            return "Attack what?";
        }
        String targetName = args[0];
        // TODO: Find the entity named 'targetName' and attack it.
        return "You attack " + targetName + "! (Not implemented)";
    }

    private String take(String[] args) {
        if (args.length == 0) {
            return "Take what?";
        }
        String itemName = args[0];
        Items itemToTake = currentLocation.findItemByName(itemName);

        if (itemToTake == null) {
            return "There is no " + itemName + " here.";
        }

        if (player.getBackpack().addItem(itemToTake)) {
            currentLocation.removeItem(itemToTake);
            return "You take the " + itemToTake.getName() + ".";
        } else {
            return "Your backpack is full.";
        }
    }

    private String use(String[] args) {
        if (args.length == 0) {
            return "Use what?";
        }
        String itemName = args[0];
        // TODO: Implement the 'use' logic based on our design.
        return "You try to use " + itemName + ". (Not implemented)";
    }

    private String quit() {
        // This command should probably signal the main game loop to exit.
        // For now, we can just return a message. A better way is to have a special return value.
        // We will use "QUIT_GAME" as a special signal.
        return "QUIT_GAME";
    }
}
