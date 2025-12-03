package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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
            return "Go where? Specify a direction or a place. The directions are: NORTH, SOUTH, EAST, WEST.";
        }
        Direction direction = Direction.valueOf(args[0]);
        if (this.currentLocation.getExits().containsKey(direction)){
            this.currentLocation = this.currentLocation.getExits().get(direction).getDestination();
         return "You go " + direction + ".";
        }
        else {
            return "You can't go that way. Try another direction. (NORTH, SOUTH, EAST, WEST)";
        }
    }

    private String help() {
        return "Available commands are: " + commandList;
    }

    private String look(String[] args) {
    if (args.length == 0) {
        String description = this.currentLocation.getDescription();
        Map<String,Items> items = this.currentLocation.getItems();

        String itemsDescription = "";
        if (!items.isEmpty()) {
            List<String> itemNames = new ArrayList<>();
            for (Items item : items.values()) {
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
        Items itemToTake = currentLocation.getItemByName(itemName);

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
        String itemName1 = args[0];

        if (args.length == 1) {
            Items itemToUse = player.getBackpack().getItemByName(itemName1);
            if (itemToUse == null) {
                return "You don't have a " + itemName1 + ".";
            }
            return itemToUse.use(player);
        }

        String itemName2 = args[1];
        Items item1 = player.getBackpack().getItemByName(itemName1);
        Items item2 = player.getBackpack().getItemByName(itemName2);

        if (item1 == null) {
            return "You don't have a " + itemName1 + ".";
        }
        if (item2 == null) {
            return "You don't have a " + itemName2 + ".";
        }

        return item1.useWith(item2, player);
    }

    private String quit() {
        return "QUIT_GAME";
    }
}
