package fr.univpoitiers.backrooms;

import java.util.Arrays;
import java.util.List;

class CommandWords {
    // Base commands MUST be in uppercase for consistent parsing
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
    private String commandList = String.join(" ", CommandWords.getValidCommands());

    /**
     * Parses and executes the user's command line.
     *
     * @param input The user's full input (e.g., "GO north").
     * @return The result message to be displayed to the user.
     */
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
                return executeSpecialCommand(commandWord, args);
            }
            return "Unknown command. Type HELP for a list of commands.";
        }

        // Execute the base command
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

// --- Implementation of base command methods ---

    public String go(String[] args) {
        if (args.length == 0) {
            return "Go where? Specify a direction or a place.";
        }
        return //TODO : Go to place
    }

    public String help(String[] args) {
        return "Available commands are: " + commandList;
    }

    public String look(String[] args) {
        if (args.length == 0) {
            return this.Locations.getDescription();
        }
        // TODO: LOOK [argument]: describe a specific object in the room
        return "You look at the object: " + args[0];
    }

    public String attack(String[] args) {
        if (args.length == 0) {
            return "Attack what?";
        }
        // TODO: Attack logic
        return "You attack " + args[0] + "! (Not implemented)";
    }

    public String take(String[] args) {
        if (args.length == 0) {
            return "Take what?";
        }
        // TODO: Take logic
        return "You pick up " + args[0] + ;
    }

    public String use(String[] args) {
        if (args.length == 0) {
            return "Use what?";
        }
        String item1 = args[0];
        String item2 = args.length > 1 ? args[1] : null;

        if (item2 != null) {
            // TODO : use 2 items logic
            return "You use " + item1 + " with " + item2 + ".";
        } else {
            // TODO : use 1 item logic
            return "You use the object " + item1 + ".";
        }
    }

    public String quit(String[] args) {
        //TODO : quit logic
        }

}
