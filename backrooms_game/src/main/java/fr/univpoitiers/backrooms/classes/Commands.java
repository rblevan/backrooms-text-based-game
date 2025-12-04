package fr.univpoitiers.backrooms.classes;

import fr.univpoitiers.backrooms.enumeration.Direction;

import javax.swing.*;
import java.util.*;

class CommandWords {
    private static final String[] VALID_COMMANDS = {
            "GO", "HELP", "LOOK", "ATTACK", "TAKE", "USE", "QUIT", "STATUS"
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
            case "STATUS":
                return status();
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

        // --- 1. LOOK sans argument : description complète ---
        if (args.length == 0) {

            String description_location = this.currentLocation.getDescription();
            Map<String, Items> items = this.currentLocation.getItems();
            Map<String, Characters> characters = this.currentLocation.getCharacters();

            String itemsDescription;
            String charactersDescription;

            // Affichage des items
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

            // Affichage des personnages
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
        // --- 2. LOOK [item] : décrire un objet spécifique ---
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

        // Chercher dans la pièce
        targetItem = this.currentLocation.getItemByName(objectToLookAtName);

        // Sinon chercher dans le sac du joueur
        if (targetItem == null) {
            targetItem = this.player.getBackpack().getItemByName(objectToLookAtName);
        }

        // Si trouvé
        if (targetItem != null) {
            return targetItem.getDescription() + "\nThe volume of this item is " + targetItem.getVolume() + " units.";
        }

        // Aucun objet trouvé
        return "You don't see " + objectToLookAtName + " here or in your backpack.";
    }

    private String attack(String[] args) {
        if (args.length < 2) {
            return "Attack what with what? Usage: ATTACK [weapon] [target]";
        }

        String weaponName = args[0];
        String targetName = args[1];

        // Cherche l'arme dans le sac
        Items item = player.getBackpack().getItemByName(weaponName);
        if (item == null) {
            return "You don't have a " + weaponName + ".";
        }

        // Vérifie que c'est bien une arme
        if (!(item instanceof Weapon)) {
            return weaponName + " is not a weapon!";
        }

        // Cherche la cible
        Entity target = currentLocation.getCharacterByName(targetName);
        if (target == null) {
            return "There is no " + targetName + " here.";
        }

        // --- Message narratif simple pour l'attaque ---
        return ((Weapon) item).use(player, target);
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

        String itemName = args[0];
        Items item = player.getBackpack().getItemByName(itemName);

        if (item == null) {
            return "You don't have a " + itemName + ".";
        }

        // --- Food : s'utilise seul ---
        if (item instanceof Food) {
            return ((Food) item).use(player); // Heal + retire du sac
        }

        // --- Les autres items nécessitent une cible ---
        if (args.length < 2) {
            return "You need to specify a target.";
        }

        String targetName = args[1];
        Entity target = currentLocation.getCharacterByName(targetName);

        if (target == null) {
            return "There is no " + targetName + " here.";
        }

        // --- Spells : sort lancé sur cible ---
        return ((Spells) item).use(player, target);
    }

    private String quit() {
        return "QUIT_GAME";
    }

    private String status(){
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
}
